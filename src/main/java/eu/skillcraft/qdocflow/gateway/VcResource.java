package eu.skillcraft.qdocflow.gateway;

import eu.skillcraft.qdocflow.approval.ApprovalFacade;
import eu.skillcraft.qdocflow.approval.SendToVerificationCommand;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class VcResource {


	private final ApprovalFacade approvalFacade;


	@PutMapping
	public ResponseEntity<Void> sendToVerification(@RequestBody SendToVerificationCommand command) {
		approvalFacade.handle(command);
		return ResponseEntity.ok().build();
	}

}


