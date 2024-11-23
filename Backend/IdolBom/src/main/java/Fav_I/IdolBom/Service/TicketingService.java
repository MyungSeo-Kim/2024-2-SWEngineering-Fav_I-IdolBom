package Fav_I.IdolBom.Service;

import Fav_I.IdolBom.DTO.TicketingFormDTO;
import Fav_I.IdolBom.Entity.Matching;
import Fav_I.IdolBom.Entity.Ticketing;
import Fav_I.IdolBom.Entity.User;
import Fav_I.IdolBom.Repository.MatchingRepository;
import Fav_I.IdolBom.Repository.ScheduleRepository;
import Fav_I.IdolBom.Repository.TicketingRepository;
import Fav_I.IdolBom.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TicketingService {
    private final TicketingRepository ticketingRepository;
    private final MatchingRepository matchingRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public List<Ticketing> getTicketingList() {
        return ticketingRepository.findNotMatched();
    }

    public void submitTicketing(User currentUser, int schedule_id, TicketingFormDTO formDTO) {
        Ticketing ticketing = new Ticketing();
        ticketing.setApplicantID(userRepository.findById(currentUser.getId()).get());
        ticketing.setScheduleID(scheduleRepository.findById(schedule_id).get());
        ticketing.setTicketNum(formDTO.getTicketNum());
        ticketing.setSeatingType(formDTO.getSeatingType());
        ticketing.setRequestMessage(formDTO.getRequestMessage());
        ticketingRepository.save(ticketing);
    }

    @Transactional
    public Matching matchTicketing(User agentUser, int ticketing_id) {
        Matching newMatch = new Matching();
        Ticketing ticketing_to_match = ticketingRepository.findById(ticketing_id)
                .orElseThrow(() -> new NullPointerException("Ticketing with id " + ticketing_id + " not found"));
        ticketing_to_match.setTicketingStatus(1); // 매칭 전 -> 매칭 후, 예매 전 상태 변경
        newMatch.setApplicantID(ticketing_to_match.getApplicantID());
        newMatch.setAgentID(agentUser);
        newMatch.setTicketingID(ticketing_to_match);
        ticketingRepository.save(ticketing_to_match);
        matchingRepository.save(newMatch);
        return newMatch;
    }

    public Ticketing reMatchTicketing(int matching_id) {
        Matching to_delete = matchingRepository.findById(matching_id).orElseThrow(
                () -> new NullPointerException("Matching with id " + matching_id + " not found"));
        Ticketing to_modify = ticketingRepository.findById(to_delete.getTicketingID().getId())
                .orElseThrow(() -> new NullPointerException("Matching with id " + matching_id + " not found"));
        if (to_modify.getIsRematch() == 1) {
            throw new NullPointerException("Matching with id " + matching_id + "is already rematched.");
        }
        else {
            to_modify.setIsRematch(1);
            to_modify.setTicketingStatus(0);
            ticketingRepository.save(to_modify);
            matchingRepository.deleteById(matching_id);
            return to_modify;
        }
    }

    public List<Ticketing> getMyTicketingList(User user) {
        return ticketingRepository.findAllByApplicantID(user);
    }
}
