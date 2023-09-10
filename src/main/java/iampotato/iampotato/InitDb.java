package iampotato.iampotato;

import iampotato.iampotato.domain.customer.domain.Customer;
import iampotato.iampotato.domain.discount.domain.Discount;
import iampotato.iampotato.domain.discount.domain.DiscountDay;
import iampotato.iampotato.domain.discount.domain.Discounts;
import iampotato.iampotato.domain.item.domain.Item;
import iampotato.iampotato.domain.item.domain.ItemCategory;
import iampotato.iampotato.domain.item.domain.Items;
import iampotato.iampotato.domain.itemoption.domain.ItemOption;
import iampotato.iampotato.domain.itemoption.domain.ItemOptionCategory;
import iampotato.iampotato.domain.order.domain.Order;
import iampotato.iampotato.domain.order.domain.OrderStatus;
import iampotato.iampotato.domain.orderitem.domain.OrderItem;
import iampotato.iampotato.domain.review.domain.Review;
import iampotato.iampotato.domain.review.domain.Reviews;
import iampotato.iampotato.domain.store.domain.*;
import iampotato.iampotato.domain.storeimage.domain.StoreImage;
import iampotato.iampotato.domain.storeimage.domain.StoreImages;
import iampotato.iampotato.domain.storeoperationhour.domain.StoreOperationHour;
import iampotato.iampotato.domain.storeoperationhour.domain.StoreOperationHours;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() throws ParseException {
        initService.dbStores1();
        initService.dbStores2();
        initService.dbStores3();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbStores1() throws ParseException {
            Store store1 = Store.builder()
                    .name("좋은원두")
                    .createdDate(LocalDateTime.now())
                    .outletNum(0)
                    .closedDay(StoreDay.FRI)
                    .address(new Address("h1", "h2", "h3", "h4"))
                    .category(StoreCategory.CAFE)
                    .location((Point) new WKTReader().read(String.format("POINT(%s %s)", 127.455409, 36.625170)))
                    .phone("01012345678")
                    .paymentType(StorePaymentType.PREPAID)
                    .salesType(StoreSalesType.HALL)
                    .description("11가지 원두를 쓰는 카페입니다")
                    .discountInfo(StoreDiscountInfo.TODAY_DISCOUNT)
                    .storeStatus(StoreStatus.OPEN)
                    .build();

            StoreOperationHour storeOperationHour1 = StoreOperationHour.builder()
                    .store(store1)
                    .startDay(StoreDay.MON)
                    .endDay(StoreDay.FRI)
                    .startTime("09:00")
                    .endTime("18:00")
                    .build();
            StoreOperationHours storeOperationHours = new StoreOperationHours(storeOperationHour1);

            StoreImage storeImage1 = StoreImage.builder()
                    .store(store1)
                    .creatTime(LocalDateTime.now())
                    .storeImg("default:1212")
                    .build();
            StoreImage storeImage2 = StoreImage.builder()
                    .store(store1)
                    .creatTime(LocalDateTime.now())
                    .storeImg("default:1213")
                    .build();
            StoreImages storeImages = new StoreImages(storeImage1, storeImage2);

            Review review1 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .greatCoffee(1)
                    .greatBeverage(1)
                    .greatFood(0)
                    .manyOutlet(0)
                    .build();

            Review review2 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .greatCoffee(0)
                    .greatBeverage(1)
                    .greatFood(1)
                    .manyOutlet(1)
                    .build();

            Review review3 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .greatCoffee(0)
                    .greatBeverage(1)
                    .greatFood(0)
                    .manyOutlet(1)
                    .build();

            Reviews reviews = new Reviews(review1, review2, review3);

            Discount discount1 = Discount.builder()
                    .discountDay(DiscountDay.MON)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discount discount2 = Discount.builder()
                    .discountDay(DiscountDay.MON)
                    .discountRatio(10)
                    .people(4)
                    .store(store1)
                    .build();

            Discount discount3 = Discount.builder()
                    .discountDay(DiscountDay.SUN)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discounts discounts = new Discounts(discount1, discount2, discount3);
//            em.persist(storeImage1);
//            em.persist(storeImage2);
//            em.persist(storeOperationHour1);

            Item item1 = Item.builder()
                    .store(store1)
                    .name("돼지불고기")
                    .category(ItemCategory.PORK)
                    .img("http://dffdf")
                    .price(5000)
                    .build();

            Item item2 = Item.builder()
                    .store(store1)
                    .name("대창")
                    .category(ItemCategory.PORK)
                    .img("http://dffdf2")
                    .price(10000)
                    .build();

            Items items = new Items(item1, item2);

            store1.updateCollection(storeOperationHours, storeImages, reviews, discounts, items);

            em.persist(store1);
        }

        public void dbStores2() throws ParseException {
            Store store1 = Store.builder()
                    .name("착한원두")
                    .createdDate(LocalDateTime.now())
                    .outletNum(0)
                    .closedDay(StoreDay.FRI)
                    .address(new Address("h1", "h2", "h3", "h4"))
                    .category(StoreCategory.CAFE)
                    .location((Point) new WKTReader().read(String.format("POINT(%s %s)", 127.455411, 36.625170)))
                    .phone("01012345679")
                    .paymentType(StorePaymentType.PREPAID)
                    .salesType(StoreSalesType.HALL)
                    .description("11가지 원두를 쓰는 카페입니다")
                    .discountInfo(StoreDiscountInfo.TODAY_DISCOUNT)
                    .storeStatus(StoreStatus.OPEN)
                    .build();

            StoreOperationHour storeOperationHour1 = StoreOperationHour.builder()
                    .store(store1)
                    .startDay(StoreDay.MON)
                    .endDay(StoreDay.FRI)
                    .startTime("09:00")
                    .endTime("18:00")
                    .build();
            StoreOperationHours storeOperationHours = new StoreOperationHours(storeOperationHour1);

            StoreImage storeImage1 = StoreImage.builder()
                    .store(store1)
                    .creatTime(LocalDateTime.now())
                    .storeImg("default:1212")
                    .build();
            StoreImage storeImage2 = StoreImage.builder()
                    .store(store1)
                    .creatTime(LocalDateTime.now())
                    .storeImg("default:1213")
                    .build();
            StoreImages storeImages = new StoreImages(storeImage1, storeImage2);

            Review review1 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .greatCoffee(1)
                    .greatBeverage(1)
                    .greatFood(0)
                    .manyOutlet(0)
                    .build();

            Review review2 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .greatCoffee(0)
                    .greatBeverage(1)
                    .greatFood(1)
                    .manyOutlet(1)
                    .build();

            Review review3 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .greatCoffee(0)
                    .greatBeverage(1)
                    .greatFood(0)
                    .manyOutlet(1)
                    .build();

            Reviews reviews = new Reviews(review1, review2, review3);

            Discount discount1 = Discount.builder()
                    .discountDay(DiscountDay.MON)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discount discount2 = Discount.builder()
                    .discountDay(DiscountDay.MON)
                    .discountRatio(10)
                    .people(4)
                    .store(store1)
                    .build();

            Discount discount3 = Discount.builder()
                    .discountDay(DiscountDay.SUN)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discounts discounts = new Discounts(discount1, discount2, discount3);
//            em.persist(storeImage1);
//            em.persist(storeImage2);
//            em.persist(storeOperationHour1);

            Item item1 = Item.builder()
                    .store(store1)
                    .name("돼지불고기")
                    .category(ItemCategory.PORK)
                    .img("http://dffdf")
                    .price(5000)
                    .build();

            Items items = new Items(item1);

            store1.updateCollection(storeOperationHours, storeImages, reviews, discounts, items);

            em.persist(store1);
        }

        public void dbStores3() throws ParseException {
            Store store1 = Store.builder()
                    .name("원두원두")
                    .createdDate(LocalDateTime.now())
                    .outletNum(0)
                    .closedDay(StoreDay.FRI)
                    .address(new Address("h1", "h2", "h3", "h4"))
                    .category(StoreCategory.CAFE)
                    .location((Point) new WKTReader().read(String.format("POINT(%s %s)", 129.455409, 34.625170)))
                    .phone("01012345678")
                    .paymentType(StorePaymentType.PREPAID)
                    .salesType(StoreSalesType.HALL)
                    .description("11가지 원두를 쓰는 카페입니다")
                    .discountInfo(StoreDiscountInfo.TODAY_DISCOUNT)
                    .storeStatus(StoreStatus.OPEN)
                    .build();

            StoreOperationHour storeOperationHour1 = StoreOperationHour.builder()
                    .store(store1)
                    .startDay(StoreDay.MON)
                    .endDay(StoreDay.FRI)
                    .startTime("09:00")
                    .endTime("18:00")
                    .build();
            StoreOperationHours storeOperationHours = new StoreOperationHours(storeOperationHour1);

            StoreImage storeImage1 = StoreImage.builder()
                    .store(store1)
                    .creatTime(LocalDateTime.now())
                    .storeImg("default:1212")
                    .build();
            StoreImage storeImage2 = StoreImage.builder()
                    .store(store1)
                    .creatTime(LocalDateTime.now())
                    .storeImg("default:1213")
                    .build();
            StoreImages storeImages = new StoreImages(storeImage1, storeImage2);

            Review review1 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .greatCoffee(1)
                    .greatBeverage(1)
                    .greatFood(0)
                    .manyOutlet(0)
                    .build();

            Review review2 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .greatCoffee(0)
                    .greatBeverage(1)
                    .greatFood(1)
                    .manyOutlet(1)
                    .build();

            Review review3 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .greatCoffee(0)
                    .greatBeverage(1)
                    .greatFood(0)
                    .manyOutlet(1)
                    .build();

            Reviews reviews = new Reviews(review1, review2, review3);

            Discount discount1 = Discount.builder()
                    .discountDay(DiscountDay.MON)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discount discount2 = Discount.builder()
                    .discountDay(DiscountDay.MON)
                    .discountRatio(10)
                    .people(4)
                    .store(store1)
                    .build();

            Discount discount3 = Discount.builder()
                    .discountDay(DiscountDay.SUN)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discounts discounts = new Discounts(discount1, discount2, discount3);
//            em.persist(storeImage1);
//            em.persist(storeImage2);
//            em.persist(storeOperationHour1);

            Item item1 = Item.builder()
                    .store(store1)
                    .name("돼지불고기")
                    .category(ItemCategory.PORK)
                    .img("http://dffdf")
                    .price(5000)
                    .build();

            Item item2 = Item.builder()
                    .store(store1)
                    .name("대창")
                    .category(ItemCategory.PORK)
                    .img("http://dffdf2")
                    .price(10000)
                    .build();

            ItemOption itemOption1 = ItemOption.builder()
                    .item(item1)
                    .name("중")
                    .price(3000)
                    .category(ItemOptionCategory.SIZE)
                    .build();

            ItemOption itemOption2 = ItemOption.builder()
                    .item(item1)
                    .name("맵게")
                    .price(0)
                    .category(ItemOptionCategory.SPICY)
                    .build();

            List<ItemOption> itemOptions = new ArrayList<>();
            itemOptions.add(itemOption1);
            itemOptions.add(itemOption2);

            item1.updateCollection(itemOptions);
            Items items = new Items(item1, item2);

            store1.updateCollection(storeOperationHours, storeImages, reviews, discounts, items);
            em.persist(store1);

            Customer customer = Customer.builder()
                    .loginId("hi")
                    .password("123")
                    .nickname("likeCoffee")
                    .build();
            em.persist(customer);

            Order order1 = Order.builder()
                    .customer(customer)
                    .orderStatus(OrderStatus.FINISH)
                    .totalPrice(8000)
                    .totalPeople(3)
                    .review(review1)
                    .build();

            OrderItem orderItem1 = OrderItem.builder()
                    .order(order1)
                    .item(item1)
                    .totalPrice(5000)
                    .build();

            OrderItem orderItem2 = OrderItem.builder()
                    .order(order1)
                    .item(item1)
                    .itemOption(itemOption1)
                    .totalPrice(3000)
                    .build();

            OrderItem orderItem3 = OrderItem.builder()
                    .order(order1)
                    .item(item1)
                    .itemOption(itemOption2)
                    .totalPrice(0)
                    .build();

            List<OrderItem> orderItems = new ArrayList<>();
            orderItems.add(orderItem1);
            orderItems.add(orderItem2);
            orderItems.add(orderItem3);

            order1.updateCollection(orderItems);
            em.persist(order1);
        }
    }
}
