package iampotato.iampotato.domain.item.api;

import iampotato.iampotato.domain.item.application.ItemService;
import iampotato.iampotato.domain.item.domain.Item;
import iampotato.iampotato.domain.item.dto.ItemRequest;
import iampotato.iampotato.domain.item.dto.ItemResponse;
import iampotato.iampotato.global.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @Tag(name = "아이템(상품)")
    @Operation(summary = "아이템", description = "아이템 id 를 주면 해당 id 에 해당하는 item 정보를 반환합니다.")
    @GetMapping("api/v1/item")
    public Result<ItemResponse> getItem(ItemRequest itemRequest) {

        Item item = itemService.getItem(itemRequest);
        ItemResponse response = new ItemResponse(item);

        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, response);
    }
}
