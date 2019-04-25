package eu.skillcraft.qdocflow.gateway;

import eu.skillcraft.qdocflow.approval.ApprovalCommand;
import eu.skillcraft.qdocflow.approval.ApprovalFacade;
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
	public ResponseEntity<Void> sendToVerification(@RequestBody ApprovalCommand.DoSendVcToVerification command) {
		approvalFacade.handle(command);
		return ResponseEntity.ok().build();
	}

}


