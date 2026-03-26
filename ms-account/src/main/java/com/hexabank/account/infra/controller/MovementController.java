package com.hexabank.account.infra.controller;

import com.hexabank.account.application.usecase.GetMovementsUseCase;
import com.hexabank.account.application.usecase.RegisterMovementUseCase;
import com.hexabank.account.domain.entity.Movement;
import com.hexabank.account.infra.controller.dto.MovementRequest;
import com.hexabank.account.infra.controller.dto.MovementResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts/{accountId}/movements")
public class MovementController {

    private final RegisterMovementUseCase registerMovementUseCase;
    private final GetMovementsUseCase getMovementsUseCase;

    public MovementController(RegisterMovementUseCase registerMovementUseCase,
                              GetMovementsUseCase getMovementsUseCase) {
        this.registerMovementUseCase = registerMovementUseCase;
        this.getMovementsUseCase = getMovementsUseCase;
    }

    @PostMapping
    public ResponseEntity<MovementResponse> register(@PathVariable Long accountId,
                                                     @Valid @RequestBody MovementRequest req) {
        Optional<Movement> maybe = registerMovementUseCase.register(
            accountId,
            req.getMovementType(),
            req.getAmount()
        );

        if (maybe.isPresent()) {
            Movement m = maybe.get();
            MovementResponse resp = map(m);
            return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<MovementResponse>> list(@PathVariable Long accountId) {
        List<MovementResponse> list = getMovementsUseCase.getByAccountId(accountId)
            .stream()
            .map(this::map)
            .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    private MovementResponse map(Movement m) {
        return new MovementResponse(m.getId(), m.getMovementType(), m.getAmount(), m.getMovementDate());
    }
}
