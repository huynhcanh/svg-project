package com.example.svg_project.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
public class MoveItemRequest {
    @NotBlank(message = "warehouse is not null or empty")
    private String warehouse;
    @NotBlank(message = "rack is not null or empty")
    private String rack;
    @NotBlank(message = "tray is not null or empty")
    private String tray;
    @NotNull(message = "items is not null")
    List<MoveDetailRequest>  items;

    @Setter
    @Getter
    public static class MoveDetailRequest{
        @NotNull(message = "id is not null") // dont work
        private Long id;
        @NotNull(message = "quantity is not null")// dont work
        @Min(value = 0, message = "quantity can't be less than 0")// dont work
        private Integer quantity;
    }
}
