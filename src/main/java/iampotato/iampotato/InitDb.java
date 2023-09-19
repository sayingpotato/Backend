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
        initService.dbStores4();
        initService.dbStores5();
        initService.dbStores6();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbStores1() throws ParseException {

            StoreMapThumbnail storeMapThumbnail = StoreMapThumbnail.builder()
                    .mainImg("https://github.com/sayingpotato/Frontend-Web/assets/64068511/f1e66d1b-c551-468b-b54e-bdc1de905999")
                    .subImg1("https://github.com/sayingpotato/Frontend-Web/assets/64068511/3f3d91b3-3b3a-41e8-8d8e-467174118a96")
                    .subImg2("https://github.com/sayingpotato/Frontend-Web/assets/64068511/65648635-b505-4c73-b18b-c381569b20a9")
                    .subImg3("https://github.com/sayingpotato/Frontend-Web/assets/64068511/1ed76463-29ca-44b2-874e-9a08c48256e1")
                    .build();

            StoreTopItem storeTopItem = StoreTopItem.builder()
                    .firstGradeItemName("아이스아메리카노")
                    .firstGradeItemNumber(299)
                    .secondGradeItemName("자몽허니블랙티")
                    .secondGradeItemNumber(142)
                    .thirdGradeItemName("핫초코")
                    .thirdGradeItemNumber(53)
                    .build();

            StoreTopReview storeTopReview = StoreTopReview.builder()
                    .firstGradeReviewName("가게가 멋있어요")
                    .firstGradeReviewNumber(47)
                    .secondGradeReviewName("커피가 맛있어요")
                    .secondGradeReviewNumber(44)
                    .thirdGradeReviewName("조명이 예뻐요")
                    .thirdGradeReviewNumber(16)
                    .build();

            Store store1 = Store.builder()
                    .name("좋은원두")
                    .createdDate(LocalDateTime.now())
                    .outletNum(0)
                    .closedDay(StoreDay.FRI)
                    .address(new Address("충북 청주시 서원구 1순환로 6720길 64", "h2", "h3", "h4"))
                    .category(StoreCategory.CAFE)
                    .location((Point) new WKTReader().read(String.format("POINT(%s %s)", 127.455409, 36.625170)))
                    .phone("01012345678")
                    .paymentType(StorePaymentType.PREPAID)
                    .salesType(StoreSalesType.HALL)
                    .description("11가지 원두를 쓰는 카페입니다")
                    .discountInfo(StoreDiscountInfo.TODAY_DISCOUNT)
                    .storeStatus(StoreStatus.OPEN)
                    .tableImg("https://github.com/sayingpotato/Frontend-Web/assets/64068511/f1e66d1b-c551-468b-b54e-bdc1de905999")
                    .storeMapThumbnail(storeMapThumbnail)
                    .storeTopItem(storeTopItem)
                    .storeTopReview(storeTopReview)
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
                    .storeImg("https://github.com/sayingpotato/Frontend-Web/assets/64068511/3f3d91b3-3b3a-41e8-8d8e-467174118a96")
                    .build();
            StoreImage storeImage2 = StoreImage.builder()
                    .store(store1)
                    .creatTime(LocalDateTime.now())
                    .storeImg("https://github.com/sayingpotato/Frontend-Web/assets/64068511/65648635-b505-4c73-b18b-c381569b20a9")
                    .build();
            StoreImage storeImage3 = StoreImage.builder()
                    .store(store1)
                    .creatTime(LocalDateTime.now())
                    .storeImg("https://github.com/sayingpotato/Frontend-Web/assets/64068511/1ed76463-29ca-44b2-874e-9a08c48256e1")
                    .build();
            StoreImage storeImage4 = StoreImage.builder()
                    .store(store1)
                    .creatTime(LocalDateTime.now())
                    .storeImg("https://github.com/sayingpotato/Frontend-Web/assets/64068511/5c7bddf0-647e-441c-a695-faf12083a933")
                    .build();
            StoreImage storeImage5 = StoreImage.builder()
                    .store(store1)
                    .creatTime(LocalDateTime.now())
                    .storeImg("https://github.com/sayingpotato/Frontend-Web/assets/64068511/6fe0fcf0-b38e-4b4e-ac1b-42627a445755")
                    .build();
            StoreImages storeImages = new StoreImages(storeImage1, storeImage2, storeImage3, storeImage4, storeImage5);

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
                    .name("아메리카노")
                    .category(ItemCategory.PORK)
                    .img("https://github.com/sayingpotato/Frontend-Web/assets/64068511/159b3769-cf0f-4d2e-8307-ba63ae71c8d3")
                    .price(5000)
                    .build();

            Item item2 = Item.builder()
                    .store(store1)
                    .name("자몽허니블랙티")
                    .category(ItemCategory.PORK)
                    .img("https://github.com/sayingpotato/Frontend-Web/assets/64068511/c95086b5-179b-4234-8ce5-0480a714fcc3")
                    .price(10000)
                    .build();

            Item item3 = Item.builder()
                    .store(store1)
                    .name("카페라떼")
                    .category(ItemCategory.PORK)
                    .img("https://github.com/sayingpotato/Frontend-Web/assets/64068511/8f777931-2110-4093-beff-c4b1f807234b")
                    .price(7000)
                    .build();

            Item item4 = Item.builder()
                    .store(store1)
                    .name("핫초코")
                    .category(ItemCategory.PORK)
                    .img("https://github.com/sayingpotato/Frontend-Web/assets/64068511/2f95dd53-70e4-47a0-b36b-2f32b7de1b55")
                    .price(4000)
                    .build();

            ItemOption itemOption1 = ItemOption.builder()
                    .item(item1)
                    .name("톨")
                    .price(0)
                    .category(ItemOptionCategory.SIZE)
                    .build();

            ItemOption itemOption2 = ItemOption.builder()
                    .item(item1)
                    .name("그란데")
                    .price(500)
                    .category(ItemOptionCategory.SIZE)
                    .build();

            ItemOption itemOption3 = ItemOption.builder()
                    .item(item1)
                    .name("벤티")
                    .price(1000)
                    .category(ItemOptionCategory.SIZE)
                    .build();

            List<ItemOption> itemOptions = new ArrayList<>();
            itemOptions.add(itemOption1);
            itemOptions.add(itemOption2);
            itemOptions.add(itemOption3);
            item1.updateCollection(itemOptions);

            ItemOption itemOption4 = ItemOption.builder()
                    .item(item2)
                    .name("톨")
                    .price(0)
                    .category(ItemOptionCategory.SIZE)
                    .build();

            ItemOption itemOption5 = ItemOption.builder()
                    .item(item2)
                    .name("그란데")
                    .price(500)
                    .category(ItemOptionCategory.SIZE)
                    .build();

            ItemOption itemOption6 = ItemOption.builder()
                    .item(item2)
                    .name("벤티")
                    .price(1000)
                    .category(ItemOptionCategory.SIZE)
                    .build();

            List<ItemOption> itemOptions2 = new ArrayList<>();
            itemOptions.add(itemOption4);
            itemOptions.add(itemOption5);
            itemOptions.add(itemOption6);
            item2.updateCollection(itemOptions2);

            ItemOption itemOption7 = ItemOption.builder()
                    .item(item3)
                    .name("톨")
                    .price(0)
                    .category(ItemOptionCategory.SIZE)
                    .build();

            ItemOption itemOption8 = ItemOption.builder()
                    .item(item3)
                    .name("그란데")
                    .price(500)
                    .category(ItemOptionCategory.SIZE)
                    .build();

            ItemOption itemOption9 = ItemOption.builder()
                    .item(item3)
                    .name("벤티")
                    .price(1000)
                    .category(ItemOptionCategory.SIZE)
                    .build();

            List<ItemOption> itemOptions3 = new ArrayList<>();
            itemOptions.add(itemOption7);
            itemOptions.add(itemOption8);
            itemOptions.add(itemOption9);
            item3.updateCollection(itemOptions3);

            ItemOption itemOption10 = ItemOption.builder()
                    .item(item4)
                    .name("톨")
                    .price(0)
                    .category(ItemOptionCategory.SIZE)
                    .build();

            ItemOption itemOption11 = ItemOption.builder()
                    .item(item4)
                    .name("그란데")
                    .price(500)
                    .category(ItemOptionCategory.SIZE)
                    .build();

            ItemOption itemOption12 = ItemOption.builder()
                    .item(item4)
                    .name("벤티")
                    .price(1000)
                    .category(ItemOptionCategory.SIZE)
                    .build();

            List<ItemOption> itemOptions4 = new ArrayList<>();
            itemOptions.add(itemOption10);
            itemOptions.add(itemOption11);
            itemOptions.add(itemOption12);
            item4.updateCollection(itemOptions4);

            Items items = new Items(item1, item2, item3, item4);

            store1.updateCollection(storeOperationHours, storeImages, reviews, discounts, items);

            em.persist(store1);
        }

        public void dbStores2() throws ParseException {

            StoreMapThumbnail storeMapThumbnail = StoreMapThumbnail.builder()
                    .mainImg("https://github.com/sayingpotato/Frontend-Web/assets/64068511/f1e66d1b-c551-468b-b54e-bdc1de905999")
                    .subImg1("https://github.com/sayingpotato/Frontend-Web/assets/64068511/3f3d91b3-3b3a-41e8-8d8e-467174118a96")
                    .subImg2("https://github.com/sayingpotato/Frontend-Web/assets/64068511/65648635-b505-4c73-b18b-c381569b20a9")
                    .subImg3("https://github.com/sayingpotato/Frontend-Web/assets/64068511/1ed76463-29ca-44b2-874e-9a08c48256e1")
                    .build();

            StoreTopItem storeTopItem = StoreTopItem.builder()
                    .firstGradeItemName("아이스아메리카노")
                    .firstGradeItemNumber(120)
                    .secondGradeItemName("녹차")
                    .secondGradeItemNumber(72)
                    .thirdGradeItemName("핫초코")
                    .thirdGradeItemNumber(40)
                    .build();

            StoreTopReview storeTopReview = StoreTopReview.builder()
                    .firstGradeReviewName("플러그가 많아요")
                    .firstGradeReviewNumber(35)
                    .secondGradeReviewName("커피가 맛있어요")
                    .secondGradeReviewNumber(24)
                    .thirdGradeReviewName("케이크가 맛나요")
                    .thirdGradeReviewNumber(9)
                    .build();

            Store store1 = Store.builder()
                    .name("착한원두")
                    .createdDate(LocalDateTime.now())
                    .outletNum(0)
                    .closedDay(StoreDay.FRI)
                    .address(new Address("h1", "h2", "h3", "h4"))
                    .category(StoreCategory.CAFE)
                    .location((Point) new WKTReader().read(String.format("POINT(%s %s)", 127.454209, 36.625170)))
                    .phone("01012345679")
                    .paymentType(StorePaymentType.PREPAID)
                    .salesType(StoreSalesType.HALL)
                    .description("11가지 원두를 쓰는 카페입니다")
                    .discountInfo(StoreDiscountInfo.TODAY_DISCOUNT)
                    .storeStatus(StoreStatus.OPEN)
                    .storeMapThumbnail(storeMapThumbnail)
                    .storeTopItem(storeTopItem)
                    .storeTopReview(storeTopReview)
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

            StoreMapThumbnail storeMapThumbnail = StoreMapThumbnail.builder()
                    .mainImg("https://github.com/sayingpotato/Frontend-Web/assets/64068511/f1e66d1b-c551-468b-b54e-bdc1de905999")
                    .subImg1("https://github.com/sayingpotato/Frontend-Web/assets/64068511/3f3d91b3-3b3a-41e8-8d8e-467174118a96")
                    .subImg2("https://github.com/sayingpotato/Frontend-Web/assets/64068511/65648635-b505-4c73-b18b-c381569b20a9")
                    .subImg3("https://github.com/sayingpotato/Frontend-Web/assets/64068511/1ed76463-29ca-44b2-874e-9a08c48256e1")
                    .build();

            StoreTopItem storeTopItem = StoreTopItem.builder()
                    .firstGradeItemName("아이스아메리카노")
                    .firstGradeItemNumber(6003)
                    .secondGradeItemName("카페라떼")
                    .secondGradeItemNumber(2642)
                    .thirdGradeItemName("돌체라떼")
                    .thirdGradeItemNumber(614)
                    .build();

            StoreTopReview storeTopReview = StoreTopReview.builder()
                    .firstGradeReviewName("분위기가 좋아요")
                    .firstGradeReviewNumber(4127)
                    .secondGradeReviewName("커피가 맛있어요")
                    .secondGradeReviewNumber(434)
                    .thirdGradeReviewName("화장실이 꺠끗해요")
                    .thirdGradeReviewNumber(126)
                    .build();

            Store store1 = Store.builder()
                    .name("원두원두")
                    .createdDate(LocalDateTime.now())
                    .outletNum(0)
                    .closedDay(StoreDay.FRI)
                    .address(new Address("h1", "h2", "h3", "h4"))
                    .category(StoreCategory.CAFE)
                    .location((Point) new WKTReader().read(String.format("POINT(%s %s)", 127.456209, 36.624170)))
                    .phone("01012345678")
                    .paymentType(StorePaymentType.PREPAID)
                    .salesType(StoreSalesType.HALL)
                    .description("11가지 원두를 쓰는 카페입니다")
                    .discountInfo(StoreDiscountInfo.NONE)
                    .storeStatus(StoreStatus.OPEN)
                    .storeMapThumbnail(storeMapThumbnail)
                    .storeTopItem(storeTopItem)
                    .storeTopReview(storeTopReview)
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
                    .customerNumber("2017038000")
                    .customerDept("소프트웨어학과")
                    .customerCollege("충북대학교")
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

        public void dbStores4() throws ParseException {
            Store store1 = Store.builder()
                    .name("스타벅스")
                    .createdDate(LocalDateTime.now())
                    .outletNum(0)
                    .closedDay(StoreDay.FRI)
                    .address(new Address("h1", "h2", "h3", "h4"))
                    .category(StoreCategory.CAFE)
                    .location((Point) new WKTReader().read(String.format("POINT(%s %s)", 127.455412, 36.629170)))
                    .phone("01012345670")
                    .paymentType(StorePaymentType.PREPAID)
                    .salesType(StoreSalesType.HALL)
                    .description("맥북만 입장가능")
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
                    .name("카라멜마끼아또")
                    .category(ItemCategory.PORK)
                    .img("http://dffdf")
                    .price(7000)
                    .build();

            Items items = new Items(item1);

            store1.updateCollection(storeOperationHours, storeImages, reviews, discounts, items);

            em.persist(store1);
        }

        public void dbStores5() throws ParseException {
            Store store1 = Store.builder()
                    .name("서브웨이")
                    .createdDate(LocalDateTime.now())
                    .outletNum(0)
                    .closedDay(StoreDay.SUN)
                    .address(new Address("h1", "h2", "h3", "h4"))
                    .category(StoreCategory.FOOD)
                    .location((Point) new WKTReader().read(String.format("POINT(%s %s)", 129.455449, 34.625160)))
                    .phone("01012345678")
                    .paymentType(StorePaymentType.PREPAID)
                    .salesType(StoreSalesType.HALL)
                    .description("신선한 야채 서브웨이")
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
                    .greatCoffee(0)
                    .greatBeverage(1)
                    .greatFood(1)
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
                    .name("BLT")
                    .category(ItemCategory.PORK)
                    .img("http://dffdf")
                    .price(12000)
                    .build();

            Item item2 = Item.builder()
                    .store(store1)
                    .name("치킨 데리야끼")
                    .category(ItemCategory.PORK)
                    .img("http://dffdf2")
                    .price(10000)
                    .build();

            ItemOption itemOption1 = ItemOption.builder()
                    .item(item1)
                    .name("30cm")
                    .price(3000)
                    .category(ItemOptionCategory.SIZE)
                    .build();

            ItemOption itemOption2 = ItemOption.builder()
                    .item(item1)
                    .name("핫칠리")
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
        }

        public void dbStores6() throws ParseException {
            Store store1 = Store.builder()
                    .name("황궁쟁반짜장")
                    .createdDate(LocalDateTime.now())
                    .outletNum(0)
                    .closedDay(StoreDay.SAT)
                    .address(new Address("h1", "h2", "h3", "h4"))
                    .category(StoreCategory.FOOD)
                    .location((Point) new WKTReader().read(String.format("POINT(%s %s)", 127.455449, 36.623165)))
                    .phone("01012345658")
                    .paymentType(StorePaymentType.PREPAID)
                    .salesType(StoreSalesType.HALL)
                    .description("신속한 배달")
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
                    .greatCoffee(0)
                    .greatBeverage(1)
                    .greatFood(1)
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
                    .greatFood(1)
                    .manyOutlet(1)
                    .build();

            Reviews reviews = new Reviews(review1, review2, review3);

            Discount discount1 = Discount.builder()
                    .discountDay(DiscountDay.TUE)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discount discount2 = Discount.builder()
                    .discountDay(DiscountDay.TUE)
                    .discountRatio(10)
                    .people(4)
                    .store(store1)
                    .build();

            Discount discount3 = Discount.builder()
                    .discountDay(DiscountDay.FRI)
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
                    .name("짜장면")
                    .category(ItemCategory.PORK)
                    .img("http://dffdf")
                    .price(7000)
                    .build();

            Item item2 = Item.builder()
                    .store(store1)
                    .name("짬뽕")
                    .category(ItemCategory.PORK)
                    .img("http://dffdf2")
                    .price(5000)
                    .build();

            ItemOption itemOption1 = ItemOption.builder()
                    .item(item1)
                    .name("곱빼기")
                    .price(500)
                    .category(ItemOptionCategory.SIZE)
                    .build();

            ItemOption itemOption2 = ItemOption.builder()
                    .item(item1)
                    .name("맛있게")
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
        }
    }
}
