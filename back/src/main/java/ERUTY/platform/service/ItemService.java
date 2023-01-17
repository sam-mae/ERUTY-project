package ERUTY.platform.service;

import ERUTY.platform.form.ItemForm;
import ERUTY.platform.domain.Item;
import ERUTY.platform.repository.ItemRepository; //

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(Item item) {
        validateDuplicateItem(item);
        itemRepository.save(item);
    }

    private void validateDuplicateItem(Item item) {
        List<Item> items = itemRepository.findItemsByDesignName(item.getDesignName());

        if(!items.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 이름의 디자인입니다.");
        }
    }

    public void validateDuplicateMember(ItemForm itemForm) {
        List<Item> items = itemRepository.findItemsByDesignName(itemForm.getDesignName());

        if(!items.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 이름의 디자인입니다.");
        }
    }
}