package hello.itemservice.domain;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();


    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }


    @Test
    void save() {
        Item itemA = new Item("itemA", 10000, 10);
        Item saveItem = itemRepository.save(itemA);
        Item findItem = itemRepository.findById(itemA.getId());
        assertThat(findItem).isEqualTo(saveItem);
    }

    @Test
    void findAll() {
        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 20000, 5);

        itemRepository.save(itemA);
        itemRepository.save(itemB);

        List<Item> result = itemRepository.findAll();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(itemA, itemB);
    }

    @Test
    void updateItem() {
        Item itemA = new Item("itemA", 10000, 1);
        Item saveItem = itemRepository.save(itemA);

        Long itemId = saveItem.getId();

        Item updateParam = new Item("itemB", 20000, 2);
        itemRepository.update(itemId, updateParam);

        Item findItem = itemRepository.findById(itemId);

        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }

}