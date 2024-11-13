package Fav_I.IdolBom.Controller;

import Fav_I.IdolBom.DTO.TicketingFormDTO;
import Fav_I.IdolBom.Entity.Ticketing;
import Fav_I.IdolBom.Entity.User;
import Fav_I.IdolBom.Service.TicketingService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TicketingController {
    private final TicketingService ticketingService;

    @GetMapping("/ticketing/list")
    public ResponseEntity<Map<String, Object>> getTicketingList() {
        Map<String, Object> response = new LinkedHashMap<>();

        try {
            List<Ticketing> ticketingList = ticketingService.getTicketingList();
            if (ticketingList.isEmpty()) {
                throw new NullPointerException("Ticketing list is empty");
            }
            response.put("code", "SU");
            response.put("message", "Success.");
            response.put("ticketing_list", ticketingList);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("code", "Error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/ticketing/submit/{schedule_id}")
    public ResponseEntity<Map<String, Object>> submitTicketing(HttpSession session, @PathVariable("schedule_id") int schedule_id, @RequestBody TicketingFormDTO formDTO) {
        Map<String, Object> response = new LinkedHashMap<>();
        Object currentUser = session.getAttribute("userInfo");

        try {
            ticketingService.submitTicketing((User)currentUser, schedule_id, formDTO);
            response.put("code", "SU");
            response.put("message", "Success.");
            response.put("submitted_user", currentUser);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch(Exception e) {
            response.put("code", "Error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
