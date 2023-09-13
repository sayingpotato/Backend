package iampotato.iampotato.domain.item.application;

import iampotato.iampotato.domain.item.dao.ItemRepository;
import iampotato.iampotato.domain.item.domain.Item;
import iampotato.iampotato.domain.item.dto.ItemRequest;
import iampotato.iampotato.domain.item.exception.ItemException;
import iampotato.iampotato.domain.item.exception.ItemExceptionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Item getItem(ItemRequest request) {

        return itemRepository.findById(request.getId())
                .orElseThrow(() -> new ItemException(ItemExceptionGroup.ITEM_NULL));
    }
}
