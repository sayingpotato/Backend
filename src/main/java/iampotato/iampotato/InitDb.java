package iampotato.iampotato;

import iampotato.iampotato.domain.customer.domain.Customer;
import iampotato.iampotato.domain.customer.domain.CustomerStatus;
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
import iampotato.iampotato.domain.owner.domain.Owner;
import iampotato.iampotato.domain.owner.domain.OwnerStatus;
import iampotato.iampotato.domain.ownerstore.domain.OwnerStore;
import iampotato.iampotato.domain.review.domain.Review;
import iampotato.iampotato.domain.review.domain.ReviewDetail;
import iampotato.iampotato.domain.review.domain.ReviewStatus;
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
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() throws ParseException {
        initService.postOwner();
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

        Owner owner;

        public void postOwner() {
            owner = Owner.builder()
                    .loginId("owner1")
                    .password("123")
                    .nickname("CafeMaster")
                    .ownerStatus(OwnerStatus.COMPLETE)
                    .ownerBusinessNumber("773-49-00806")
                    .ssn("934920")
                    .build();
        }

        public void dbStores1() throws ParseException {

            StoreMapThumbnail storeMapThumbnail = StoreMapThumbnail.builder()
                    .mainImg("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a1.png")
                    .subImg1("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a2.png")
                    .subImg2("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a3.png")
                    .subImg3("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a4.png")
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
                    .storeTodayDiscountThumbnail("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/t1.png")
                    .createdDate(LocalDateTime.now())
                    .outletNum(0)
                    .closedDay(StoreDay.FRI)
                    .address(new Address("충북 청주시 서원구 1순환로 6720길 64", "h2", "h3", "h4"))
                    .category(StoreCategory.CAFE)
                    .location((Point) new WKTReader().read(String.format("POINT(%s %s)", 127.455409, 36.625170)))
                    .phone("010-1234-5678")
                    .paymentType(StorePaymentType.PREPAID)
                    .salesType(StoreSalesType.HALL)
                    .description("11가지 원두를 쓰는 카페입니다")
                    .discountInfo(StoreDiscountInfo.TODAY_DISCOUNT)
                    .storeStatus(StoreStatus.OPEN)
                    .tableImg("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a5.png")
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
                    .storeImg("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a2.png")
                    .build();
            StoreImage storeImage2 = StoreImage.builder()
                    .store(store1)
                    .creatTime(LocalDateTime.now())
                    .storeImg("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a3.png")
                    .build();
            StoreImage storeImage3 = StoreImage.builder()
                    .store(store1)
                    .creatTime(LocalDateTime.now())
                    .storeImg("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a4.png")
                    .build();
            StoreImage storeImage4 = StoreImage.builder()
                    .store(store1)
                    .creatTime(LocalDateTime.now())
                    .storeImg("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a6.png")
                    .build();
            StoreImage storeImage5 = StoreImage.builder()
                    .store(store1)
                    .creatTime(LocalDateTime.now())
                    .storeImg("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a1.png")
                    .build();
            StoreImages storeImages = new StoreImages(storeImage1, storeImage2, storeImage3, storeImage4, storeImage5);

            ReviewDetail reviewDetail1 = ReviewDetail.GREAT_COFFEE;

            ReviewDetail reviewDetail2 = ReviewDetail.GREAT_BEVERAGE;

            ReviewDetail reviewDetail3 = ReviewDetail.GREAT_FOOD;

            ReviewDetail reviewDetail4 = ReviewDetail.MANY_OUTLET;


            List<ReviewDetail> reviewDetails1 = new ArrayList<>();
            reviewDetails1.add(reviewDetail1);
            reviewDetails1.add(reviewDetail2);

            List<ReviewDetail> reviewDetails2 = new ArrayList<>();
            reviewDetails2.add(reviewDetail2);
            reviewDetails2.add(reviewDetail3);
            reviewDetails2.add(reviewDetail4);

            List<ReviewDetail> reviewDetails3 = new ArrayList<>();
            reviewDetails3.add(reviewDetail2);
            reviewDetails3.add(reviewDetail4);

            Review review1 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .reviewDetails(reviewDetails1)
                    .build();

            Review review2 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .reviewDetails(reviewDetails2)
                    .build();

            Review review3 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .reviewDetails(reviewDetails3)
                    .build();

            Reviews reviews = new Reviews(review1, review2, review3);

            Discount discount1 = Discount.builder()
                    .discountDay(DiscountDay.FRI)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discount discount2 = Discount.builder()
                    .discountDay(DiscountDay.FRI)
                    .discountRatio(10)
                    .people(4)
                    .store(store1)
                    .build();

            Discount discount3 = Discount.builder()
                    .discountDay(DiscountDay.MON)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discount discount4 = Discount.builder()
                    .discountDay(DiscountDay.WED)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discount discount5 = Discount.builder()
                    .discountDay(DiscountDay.TUE)
                    .discountRatio(10)
                    .people(4)
                    .store(store1)
                    .build();

            Discount discount6 = Discount.builder()
                    .discountDay(DiscountDay.THU)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discount discount7 = Discount.builder()
                    .discountDay(DiscountDay.WED)
                    .discountRatio(10)
                    .people(4)
                    .store(store1)
                    .build();

            Discounts discounts = new Discounts(discount1, discount2, discount3, discount4, discount5, discount6, discount7);
//            em.persist(storeImage1);
//            em.persist(storeImage2);
//            em.persist(storeOperationHour1);

            Item item1 = Item.builder()
                    .store(store1)
                    .name("아메리카노")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/b1.png")
                    .price(5000)
                    .build();

            Item item2 = Item.builder()
                    .store(store1)
                    .name("자몽허니블랙티")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/b2.png")
                    .price(10000)
                    .build();

            Item item3 = Item.builder()
                    .store(store1)
                    .name("카페라떼")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/b3.png")
                    .price(7000)
                    .build();

            Item item4 = Item.builder()
                    .store(store1)
                    .name("핫초코")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/b4.png")
                    .price(4000)
                    .build();

            Item item5 = Item.builder()
                    .store(store1)
                    .name("고구마라떼")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c8.jpg")
                    .price(4000)
                    .build();

            Item item6 = Item.builder()
                    .store(store1)
                    .name("흑당라떼")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c7.jpg")
                    .price(4000)
                    .build();

            Item item7 = Item.builder()
                    .store(store1)
                    .name("토피넛라떼")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c6.jpg")
                    .price(4000)
                    .build();

            Item item8 = Item.builder()
                    .store(store1)
                    .name("헤이즐넛라떼")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c5.jpg")
                    .price(4000)
                    .build();

            Item item9 = Item.builder()
                    .store(store1)
                    .name("바닐라라떼")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c3.jpg")
                    .price(4000)
                    .build();

            Item item10 = Item.builder()
                    .store(store1)
                    .name("드립커피")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c2.jpg")
                    .price(4000)
                    .build();

            Item item11 = Item.builder()
                    .store(store1)
                    .name("에스프레소")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c1.jpg")
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

            Items items = new Items(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11);

            store1.updateCollection(storeOperationHours, storeImages, reviews, discounts, items);

            em.persist(store1);

            OwnerStore ownerStore = OwnerStore.builder()
                    .store(store1)
                    .owner(owner)
                    .build();
            owner.addOwnerStores(ownerStore);
        }

        public void dbStores2() throws ParseException {

            StoreMapThumbnail storeMapThumbnail = StoreMapThumbnail.builder()
                    .mainImg("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a1.png")
                    .subImg1("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a2.png")
                    .subImg2("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a3.png")
                    .subImg3("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a4.png")
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
                    .name("메가커피")
                    .storeTodayDiscountThumbnail("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/t2.png")
                    .createdDate(LocalDateTime.now())
                    .outletNum(0)
                    .closedDay(StoreDay.FRI)
                    .address(new Address("충북 청주시 흥덕구 신율로180번길 52", "h2", "h3", "h4"))
                    .category(StoreCategory.CAFE)
                    .location((Point) new WKTReader().read(String.format("POINT(%s %s)", 127.454209, 36.625170)))
                    .phone("010-6254-1859")
                    .paymentType(StorePaymentType.PREPAID)
                    .salesType(StoreSalesType.HALL)
                    .description("대용량 커피를 저렴한 가격에 드실 수 있는 커피할인점")
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
                    .storeImg("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a2.png")
                    .build();
            StoreImage storeImage2 = StoreImage.builder()
                    .store(store1)
                    .creatTime(LocalDateTime.now())
                    .storeImg("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a3.png")
                    .build();
            StoreImages storeImages = new StoreImages(storeImage1, storeImage2);

            ReviewDetail reviewDetail1 = ReviewDetail.GREAT_COFFEE;

            ReviewDetail reviewDetail2 = ReviewDetail.GREAT_BEVERAGE;

            ReviewDetail reviewDetail3 = ReviewDetail.GREAT_FOOD;

            ReviewDetail reviewDetail4 = ReviewDetail.MANY_OUTLET;


            List<ReviewDetail> reviewDetails1 = new ArrayList<>();
            reviewDetails1.add(reviewDetail1);
            reviewDetails1.add(reviewDetail2);

            List<ReviewDetail> reviewDetails2 = new ArrayList<>();
            reviewDetails2.add(reviewDetail2);
            reviewDetails2.add(reviewDetail3);
            reviewDetails2.add(reviewDetail4);

            List<ReviewDetail> reviewDetails3 = new ArrayList<>();
            reviewDetails3.add(reviewDetail2);
            reviewDetails3.add(reviewDetail4);

            Review review1 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .reviewDetails(reviewDetails1)
                    .build();

            Review review2 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .reviewDetails(reviewDetails2)
                    .build();

            Review review3 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .reviewDetails(reviewDetails3)
                    .build();

            Reviews reviews = new Reviews(review1, review2, review3);

            Discount discount1 = Discount.builder()
                    .discountDay(DiscountDay.FRI)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discount discount2 = Discount.builder()
                    .discountDay(DiscountDay.FRI)
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

            Discount discount4 = Discount.builder()
                    .discountDay(DiscountDay.WED)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discount discount5 = Discount.builder()
                    .discountDay(DiscountDay.TUE)
                    .discountRatio(10)
                    .people(4)
                    .store(store1)
                    .build();

            Discount discount6 = Discount.builder()
                    .discountDay(DiscountDay.THU)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discounts discounts = new Discounts(discount1, discount2, discount3, discount4, discount5, discount6);
//            em.persist(storeImage1);
//            em.persist(storeImage2);
//            em.persist(storeOperationHour1);

            Item item1 = Item.builder()
                    .store(store1)
                    .name("아메리카노")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/b1.png")
                    .price(5000)
                    .build();

            Item item2 = Item.builder()
                    .store(store1)
                    .name("자몽허니블랙티")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/b2.png")
                    .price(10000)
                    .build();

            Item item3 = Item.builder()
                    .store(store1)
                    .name("카페라떼")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/b3.png")
                    .price(7000)
                    .build();

            Item item4 = Item.builder()
                    .store(store1)
                    .name("핫초코")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/b4.png")
                    .price(4000)
                    .build();

            Item item5 = Item.builder()
                    .store(store1)
                    .name("고구마라떼")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c8.jpg")
                    .price(4000)
                    .build();

            Item item6 = Item.builder()
                    .store(store1)
                    .name("흑당라떼")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c7.jpg")
                    .price(4000)
                    .build();

            Item item7 = Item.builder()
                    .store(store1)
                    .name("토피넛라떼")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c6.jpg")
                    .price(4000)
                    .build();

            Item item8 = Item.builder()
                    .store(store1)
                    .name("헤이즐넛라떼")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c5.jpg")
                    .price(4000)
                    .build();

            Item item9 = Item.builder()
                    .store(store1)
                    .name("바닐라라떼")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c3.jpg")
                    .price(4000)
                    .build();

            Item item10 = Item.builder()
                    .store(store1)
                    .name("드립커피")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c2.jpg")
                    .price(4000)
                    .build();

            Item item11 = Item.builder()
                    .store(store1)
                    .name("에스프레소")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c1.jpg")
                    .price(4000)
                    .build();

            Items items = new Items(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11);

            store1.updateCollection(storeOperationHours, storeImages, reviews, discounts, items);

            em.persist(store1);
            OwnerStore ownerStore = OwnerStore.builder()
                    .store(store1)
                    .owner(owner)
                    .build();
            owner.addOwnerStores(ownerStore);
        }

        public void dbStores3() throws ParseException {

            StoreMapThumbnail storeMapThumbnail = StoreMapThumbnail.builder()
                    .mainImg("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a1.png")
                    .subImg1("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a2.png")
                    .subImg2("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a3.png")
                    .subImg3("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a4.png")
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
                    .name("투썸플레잇스")
                    .storeTodayDiscountThumbnail("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/t1.png")
                    .createdDate(LocalDateTime.now())
                    .outletNum(0)
                    .closedDay(StoreDay.FRI)
                    .address(new Address("충북 청주시 흥덕구 성봉로279번길 3", "h2", "h3", "h4"))
                    .category(StoreCategory.CAFE)
                    .location((Point) new WKTReader().read(String.format("POINT(%s %s)", 127.456209, 36.624170)))
                    .phone("010-2525-2424")
                    .paymentType(StorePaymentType.PREPAID)
                    .salesType(StoreSalesType.HALL)
                    .description("최고급 커피를 맛볼 수 있는 커피전문점")
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
                    .storeImg("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a2.png")
                    .build();
            StoreImage storeImage2 = StoreImage.builder()
                    .store(store1)
                    .creatTime(LocalDateTime.now())
                    .storeImg("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a3.png")
                    .build();
            StoreImages storeImages = new StoreImages(storeImage1, storeImage2);

            ReviewDetail reviewDetail1 = ReviewDetail.GREAT_COFFEE;

            ReviewDetail reviewDetail2 = ReviewDetail.GREAT_BEVERAGE;

            ReviewDetail reviewDetail3 = ReviewDetail.GREAT_FOOD;

            ReviewDetail reviewDetail4 = ReviewDetail.MANY_OUTLET;


            List<ReviewDetail> reviewDetails1 = new ArrayList<>();


            List<ReviewDetail> reviewDetails2 = new ArrayList<>();


            List<ReviewDetail> reviewDetails3 = new ArrayList<>();
            reviewDetails3.add(reviewDetail2);
            reviewDetails3.add(reviewDetail4);

            Review review1 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .reviewDetails(reviewDetails1)
                    .reviewStatus(ReviewStatus.NONE)
                    .build();

            Review review2 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .reviewDetails(reviewDetails2)
                    .reviewStatus(ReviewStatus.NONE)
                    .build();

            Review review3 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .reviewDetails(reviewDetails3)
                    .reviewStatus(ReviewStatus.REVIEWING)
                    .build();

            Review review4 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .reviewDetails(reviewDetails3)
                    .reviewStatus(ReviewStatus.EXPIRED)
                    .build();

            Reviews reviews = new Reviews(review1, review2, review3, review4);

            Discount discount1 = Discount.builder()
                    .discountDay(DiscountDay.FRI)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discount discount2 = Discount.builder()
                    .discountDay(DiscountDay.FRI)
                    .discountRatio(10)
                    .people(4)
                    .store(store1)
                    .build();

            Discount discount3 = Discount.builder()
                    .discountDay(DiscountDay.MON)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discount discount5 = Discount.builder()
                    .discountDay(DiscountDay.WED)
                    .discountRatio(10)
                    .people(4)
                    .store(store1)
                    .build();

            Discount discount6 = Discount.builder()
                    .discountDay(DiscountDay.THU)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discounts discounts = new Discounts(discount1, discount2, discount3, discount5, discount6);
//            em.persist(storeImage1);
//            em.persist(storeImage2);
//            em.persist(storeOperationHour1);

            Item item1 = Item.builder()
                    .store(store1)
                    .name("고구마라떼")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c8.jpg")
                    .price(5000)
                    .build();

            Item item2 = Item.builder()
                    .store(store1)
                    .name("흑당라떼")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c7.jpg")
                    .price(10000)
                    .build();

            Item item3 = Item.builder()
                    .store(store1)
                    .name("토피넛라떼")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c6.jpg")
                    .price(9000)
                    .build();

            Item item4 = Item.builder()
                    .store(store1)
                    .name("헤이즐넛라떼")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c5.jpg")
                    .price(10000)
                    .build();

            Item item5 = Item.builder()
                    .store(store1)
                    .name("카푸치노")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c4.jpg")
                    .price(7000)
                    .build();

            Item item6 = Item.builder()
                    .store(store1)
                    .name("아메리카노")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/b1.png")
                    .price(4500)
                    .build();

            Item item7 = Item.builder()
                    .store(store1)
                    .name("바닐라라떼")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c3.jpg")
                    .price(5500)
                    .build();

            Item item8 = Item.builder()
                    .store(store1)
                    .name("드립커피")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c2.jpg")
                    .price(6000)
                    .build();

            Item item9 = Item.builder()
                    .store(store1)
                    .name("카페라떼")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/b3.png")
                    .price(5000)
                    .build();

            Item item10 = Item.builder()
                    .store(store1)
                    .name("에스프레소")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c1.jpg")
                    .price(4000)
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
            Items items = new Items(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10);

            store1.updateCollection(storeOperationHours, storeImages, reviews, discounts, items);
            em.persist(store1);

            Customer customer = Customer.builder()
                    .loginId("hi")
                    .password("123")
                    .nickname("likeCoffee")
                    .customerNumber("2017038000")
                    .customerDept("소프트웨어학과")
                    .customerCollege("충북대학교")
                    .customerGrade("4학년")
                    .customerStatus(CustomerStatus.COMPLETE)
                    .createdDate(LocalDateTime.now())
                    .build();
            em.persist(customer);

            Customer customer1 = Customer.builder()
                    .loginId("hi2")
                    .password("123")
                    .nickname("goodCoffee")
                    .customerNumber("2017038001")
                    .customerDept("소프트웨어학과")
                    .customerCollege("충북대학교")
                    .customerGrade("4학년")
                    .customerStatus(CustomerStatus.COMPLETE)
                    .createdDate(LocalDateTime.now())
                    .build();
            em.persist(customer1);

            // 임시 가게
            Store store2 = Store.builder()
                    .name("메달론")
                    .storeTodayDiscountThumbnail("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/t1.png")
                    .createdDate(LocalDateTime.now())
                    .outletNum(0)
                    .closedDay(StoreDay.FRI)
                    .address(new Address("장구봉로", "h2", "h3", "h4"))
                    .category(StoreCategory.CAFE)
                    .location((Point) new WKTReader().read(String.format("POINT(%s %s)", 127.456229, 36.624270)))
                    .phone("01014254678")
                    .paymentType(StorePaymentType.PREPAID)
                    .salesType(StoreSalesType.HALL)
                    .description("매일 맛있는 커피")
                    .discountInfo(StoreDiscountInfo.NONE)
                    .storeStatus(StoreStatus.OPEN)
                    .storeMapThumbnail(storeMapThumbnail)
                    .storeTopItem(storeTopItem)
                    .storeTopReview(storeTopReview)
                    .build();

            StoreOperationHour storeOperationHour2 = StoreOperationHour.builder()
                    .store(store2)
                    .startDay(StoreDay.MON)
                    .endDay(StoreDay.FRI)
                    .startTime("09:00")
                    .endTime("18:00")
                    .build();
            StoreOperationHours storeOperationHours2 = new StoreOperationHours(storeOperationHour2);

            StoreImage storeImage3 = StoreImage.builder()
                    .store(store2)
                    .creatTime(LocalDateTime.now())
                    .storeImg("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a4.png")
                    .build();
            StoreImages storeImages2 = new StoreImages(storeImage3);

            Review review5 = Review.builder()
                    .store(store2)
                    .createdDate(LocalDateTime.now())
                    .reviewDetails(reviewDetails3)
                    .reviewStatus(ReviewStatus.EXPIRED)
                    .build();

            Reviews reviews2 = new Reviews(review5);

            Discount discount4 = Discount.builder()
                    .discountDay(DiscountDay.SUN)
                    .discountRatio(5)
                    .people(2)
                    .store(store2)
                    .build();

            Discounts discounts2 = new Discounts(discount4);
//            em.persist(storeImage1);
//            em.persist(storeImage2);
//            em.persist(storeOperationHour1);

            Item item11 = Item.builder()
                    .store(store1)
                    .name("아메리카노")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/b1.png")
                    .price(5000)
                    .build();

            Item item12 = Item.builder()
                    .store(store1)
                    .name("자몽허니블랙티")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/b2.png")
                    .price(10000)
                    .build();

            Item item13 = Item.builder()
                    .store(store1)
                    .name("카페라떼")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/b3.png")
                    .price(7000)
                    .build();

            ItemOption itemOption3 = ItemOption.builder()
                    .item(item11)
                    .name("라지")
                    .price(3000)
                    .category(ItemOptionCategory.SIZE)
                    .build();

            ItemOption itemOption4 = ItemOption.builder()
                    .item(item11)
                    .name("연하게")
                    .price(0)
                    .category(ItemOptionCategory.SPICY)
                    .build();

            List<ItemOption> itemOptions2 = new ArrayList<>();
            itemOptions2.add(itemOption3);
            itemOptions2.add(itemOption4);

            item11.updateCollection(itemOptions);
            Items items2 = new Items(item11, item12, item13);

            store1.updateCollection(storeOperationHours2, storeImages2, reviews2, discounts2, items2);
            em.persist(store2);



            OwnerStore ownerStore = OwnerStore.builder()
                    .store(store1)
                    .owner(owner)
                    .build();

            OwnerStore ownerStore1 = OwnerStore.builder()
                    .store(store2)
                    .owner(owner)
                    .build();

            List<OwnerStore> ownerStores = new ArrayList<>();
            ownerStores.add(ownerStore);
            ownerStores.add(ownerStore1);

            Owner owner2 = Owner.builder()
                    .loginId("owner2")
                    .password("123")
                    .nickname("FoodMaster")
                    .ownerStatus(OwnerStatus.UNAUTHORIZED)
                    .ownerBusinessNumber("43202-24-806")
                    .ssn("133340")
                    .build();

            owner.addOwnerStores(ownerStore);
            owner.addOwnerStores(ownerStore1);

            em.persist(owner2);

            Order order1 = Order.builder()
                    .customer(customer)
                    .orderStatus(OrderStatus.FINISH)
                    .totalPrice(8000)
                    .totalPeople(3)
                    .review(review1)
                    .createdDate(LocalDateTime.now())
                    .discountPrice(800)
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

            Order order2 = Order.builder()
                    .customer(customer)
                    .orderStatus(OrderStatus.FINISH)
                    .totalPrice(12000)
                    .totalPeople(4)
                    .createdDate(LocalDateTime.now())
                    .review(review2)
                    .discountPrice(2400)
                    .build();

            OrderItem orderItem4 = OrderItem.builder()
                    .order(order2)
                    .item(item1)
                    .totalPrice(7000)
                    .build();

            OrderItem orderItem5 = OrderItem.builder()
                    .order(order2)
                    .item(item1)
                    .itemOption(itemOption1)
                    .totalPrice(4000)
                    .build();

            OrderItem orderItem6 = OrderItem.builder()
                    .order(order2)
                    .item(item1)
                    .itemOption(itemOption2)
                    .totalPrice(1000)
                    .build();

            List<OrderItem> orderItems2 = new ArrayList<>();
            orderItems2.add(orderItem4);
            orderItems2.add(orderItem5);
            orderItems2.add(orderItem6);

            order2.updateCollection(orderItems2);

            Order order3 = Order.builder()
                    .customer(customer)
                    .orderStatus(OrderStatus.FINISH)
                    .totalPrice(7000)
                    .totalPeople(2)
                    .createdDate(LocalDateTime.now().minusHours(2))
                    .review(review3)
                    .discountPrice(700)
                    .build();

            OrderItem orderItem7 = OrderItem.builder()
                    .order(order3)
                    .item(item1)
                    .totalPrice(7000)
                    .build();

            List<OrderItem> orderItems3 = new ArrayList<>();
            orderItems3.add(orderItem7);

            order3.updateCollection(orderItems3);

            Order order4 = Order.builder()
                    .customer(customer)
                    .orderStatus(OrderStatus.ORDER)
                    .totalPrice(20000)
                    .totalPeople(1)
                    .review(review4)
                    .createdDate(LocalDateTime.now().minusHours(4))
                    .discountPrice(1000)
                    .build();

            OrderItem orderItem8 = OrderItem.builder()
                    .order(order4)
                    .item(item1)
                    .totalPrice(20000)
                    .build();

            List<OrderItem> orderItems4 = new ArrayList<>();
            orderItems4.add(orderItem8);

            order4.updateCollection(orderItems4);


            // Random 객체 생성
            Random random = new Random();

            // 5000에서 20000 사이의 랜덤한 값을 얻기
            int minPrice = 5000;
            int maxPrice = 40000;
            int step = 1000;

            // 1에서 4 사이의 랜덤한 사람 값을 얻기
            int minPeople = 1;
            int maxPeople = 4;

            // 아이템 목록
            List<Item> randomItems = new ArrayList<>();
            randomItems.add(item6);
            randomItems.add(item7);
            randomItems.add(item8);
            randomItems.add(item9);
            randomItems.add(item10);

            for(int i=0; i<6000; i++) {
                // 2023년 10월 1일부터 2023년 10월 31일까지의 랜덤 시각 생성
                int year = 2023;
                int month = 10;
                int day = random.nextInt(31) + 1; // 1부터 31 중 랜덤한 날짜 선택
                int hour = random.nextInt(8) + 13; // 13부터 21 중 랜덤한 시간 선택
                int minute = random.nextInt(60); // 0부터 59 중 랜덤한 분 선택
                int second = random.nextInt(60); // 0부터 59 중 랜덤한 초 선택

                LocalDateTime time = LocalDateTime.of(year, Month.of(month), day, hour, minute, second);

                // 1000으로 나누어떨어지는 가격 난수 생성
                int randomPrice = minPrice + step * random.nextInt((maxPrice - minPrice) / step + 1);

                // 랜덤한 사람 수 생성
                int randomPeople = minPeople + random.nextInt(maxPeople - minPeople + 1);

                int price = randomPrice;
                int people = randomPeople;

                // 아이템 랜덤 선택
                Item randomItem = randomItems.get(random.nextInt(randomItems.size()));

                Order order5 = Order.builder()
                        .customer(customer)
                        .orderStatus(OrderStatus.FINISH)
                        .totalPrice(price)
                        .totalPeople(people)
                        .review(review3)
                        .createdDate(time)
                        .discountPrice(((int)Math.ceil(price*0.1)/100)*100)
                        .build();

                OrderItem orderItem = OrderItem.builder()
                        .order(order5)
                        .item(randomItem)
                        .totalPrice(price)
                        .build();

                List<OrderItem> orderItems1 = new ArrayList<>();
                orderItems1.add(orderItem);

                order5.updateCollection(orderItems1);
                em.persist(order5);
            }

            for(int j=0; j<3000; j++) {
                // 2023년 10월 1일부터 2023년 10월 31일까지의 랜덤 시각 생성
                int year = 2023;
                int month = 10;
                int day = random.nextInt(31) + 1; // 1부터 31 중 랜덤한 날짜 선택
                int hour = random.nextInt(24); // 0부터 24 중 랜덤한 시간 선택
                int minute = random.nextInt(60); // 0부터 59 중 랜덤한 분 선택
                int second = random.nextInt(60); // 0부터 59 중 랜덤한 초 선택

                LocalDateTime time = LocalDateTime.of(year, Month.of(month), day, hour, minute, second);

                // 1000으로 나누어떨어지는 가격 난수 생성
                int randomPrice = minPrice + step * random.nextInt((maxPrice - minPrice) / step + 1);

                // 랜덤한 사람 수 생성
                int randomPeople = minPeople + random.nextInt(maxPeople - minPeople + 1);

                int price = randomPrice;
                int people = randomPeople;

                // 아이템 랜덤 선택
                Item randomItem = randomItems.get(random.nextInt(randomItems.size()));

                Order order5 = Order.builder()
                        .customer(customer)
                        .orderStatus(OrderStatus.ORDER)
                        .totalPrice(price)
                        .totalPeople(people)
                        .review(review3)
                        .createdDate(time)
                        .discountPrice(((int)Math.ceil(price*0.1)/100)*100)
                        .build();

                OrderItem orderItem = OrderItem.builder()
                        .order(order5)
                        .item(randomItem)
                        .totalPrice(price)
                        .build();

                List<OrderItem> orderItems1 = new ArrayList<>();
                orderItems1.add(orderItem);

                order5.updateCollection(orderItems1);
                em.persist(order5);
            }

            for(int j=0; j<3000; j++) {
                // 2023년 10월 1일부터 2023년 10월 31일까지의 랜덤 시각 생성
                int year = 2023;
                int month = 9;
                int day = random.nextInt(30) + 1; // 1부터 31 중 랜덤한 날짜 선택
                int hour = random.nextInt(24); // 0부터 24 중 랜덤한 시간 선택
                int minute = random.nextInt(60); // 0부터 59 중 랜덤한 분 선택
                int second = random.nextInt(60); // 0부터 59 중 랜덤한 초 선택

                LocalDateTime time = LocalDateTime.of(year, Month.of(month), day, hour, minute, second);

                // 1000으로 나누어떨어지는 가격 난수 생성
                int randomPrice = minPrice + step * random.nextInt((maxPrice - minPrice) / step + 1);

                // 랜덤한 사람 수 생성
                int randomPeople = minPeople + random.nextInt(maxPeople - minPeople + 1);

                int price = randomPrice;
                int people = randomPeople;

                // 아이템 랜덤 선택
                Item randomItem = randomItems.get(random.nextInt(randomItems.size()));

                Order order5 = Order.builder()
                        .customer(customer)
                        .orderStatus(OrderStatus.ORDER)
                        .totalPrice(price)
                        .totalPeople(people)
                        .review(review3)
                        .createdDate(time)
                        .discountPrice(((int)Math.ceil(price*0.1)/100)*100)
                        .build();

                OrderItem orderItem = OrderItem.builder()
                        .order(order5)
                        .item(randomItem)
                        .totalPrice(price)
                        .build();

                List<OrderItem> orderItems1 = new ArrayList<>();
                orderItems1.add(orderItem);

                order5.updateCollection(orderItems1);
                em.persist(order5);
            }
            em.persist(order1);
            em.persist(order2);
            em.persist(order3);
            em.persist(order4);
        }

        public void dbStores4() throws ParseException {
            Store store1 = Store.builder()
                    .name("스타벅스")
                    .storeTodayDiscountThumbnail("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/t3.png")
                    .createdDate(LocalDateTime.now())
                    .outletNum(3)
                    .closedDay(StoreDay.FRI)
                    .address(new Address("충북 청주시 서원구 내수동로 113", "h2", "h3", "h4"))
                    .category(StoreCategory.CAFE)
                    .location((Point) new WKTReader().read(String.format("POINT(%s %s)", 127.455412, 36.629170)))
                    .phone("043-268-7466")
                    .paymentType(StorePaymentType.PREPAID)
                    .salesType(StoreSalesType.HALL)
                    .description("고객을 우선시하는, 고객을 위한 커피전문점")
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
                    .storeImg("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a2.png")
                    .build();
            StoreImage storeImage2 = StoreImage.builder()
                    .store(store1)
                    .creatTime(LocalDateTime.now())
                    .storeImg("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a3.png")
                    .build();
            StoreImages storeImages = new StoreImages(storeImage1, storeImage2);

            ReviewDetail reviewDetail1 = ReviewDetail.GREAT_COFFEE;

            ReviewDetail reviewDetail2 = ReviewDetail.GREAT_BEVERAGE;

            ReviewDetail reviewDetail3 = ReviewDetail.GREAT_FOOD;

            ReviewDetail reviewDetail4 = ReviewDetail.MANY_OUTLET;


            List<ReviewDetail> reviewDetails1 = new ArrayList<>();
            reviewDetails1.add(reviewDetail4);

            List<ReviewDetail> reviewDetails2 = new ArrayList<>();
            reviewDetails2.add(reviewDetail2);
            reviewDetails2.add(reviewDetail3);

            List<ReviewDetail> reviewDetails3 = new ArrayList<>();
            reviewDetails3.add(reviewDetail2);

            Review review1 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .reviewDetails(reviewDetails1)
                    .build();

            Review review2 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .reviewDetails(reviewDetails2)
                    .build();

            Review review3 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .reviewDetails(reviewDetails3)
                    .build();

            Reviews reviews = new Reviews(review1, review2, review3);

            Discount discount1 = Discount.builder()
                    .discountDay(DiscountDay.FRI)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discount discount2 = Discount.builder()
                    .discountDay(DiscountDay.FRI)
                    .discountRatio(10)
                    .people(4)
                    .store(store1)
                    .build();

            Discount discount3 = Discount.builder()
                    .discountDay(DiscountDay.MON)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discount discount4 = Discount.builder()
                    .discountDay(DiscountDay.WED)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discount discount5 = Discount.builder()
                    .discountDay(DiscountDay.TUE)
                    .discountRatio(10)
                    .people(4)
                    .store(store1)
                    .build();

            Discount discount6 = Discount.builder()
                    .discountDay(DiscountDay.THU)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discounts discounts = new Discounts(discount1, discount2, discount3, discount4, discount5, discount6);
//            em.persist(storeImage1);
//            em.persist(storeImage2);
//            em.persist(storeOperationHour1);

            Item item1 = Item.builder()
                    .store(store1)
                    .name("아메리카노")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/b1.png")
                    .price(5000)
                    .build();

            Item item2 = Item.builder()
                    .store(store1)
                    .name("자몽허니블랙티")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/b2.png")
                    .price(10000)
                    .build();

            Item item3 = Item.builder()
                    .store(store1)
                    .name("카페라떼")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/b3.png")
                    .price(7000)
                    .build();

            Item item4 = Item.builder()
                    .store(store1)
                    .name("핫초코")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/b4.png")
                    .price(4000)
                    .build();

            Item item5 = Item.builder()
                    .store(store1)
                    .name("고구마라떼")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c8.jpg")
                    .price(4000)
                    .build();

            Item item6 = Item.builder()
                    .store(store1)
                    .name("흑당라떼")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c7.jpg")
                    .price(4000)
                    .build();

            Item item7 = Item.builder()
                    .store(store1)
                    .name("토피넛라떼")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c6.jpg")
                    .price(4000)
                    .build();

            Item item8 = Item.builder()
                    .store(store1)
                    .name("헤이즐넛라떼")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c5.jpg")
                    .price(4000)
                    .build();

            Item item9 = Item.builder()
                    .store(store1)
                    .name("바닐라라떼")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c3.jpg")
                    .price(4000)
                    .build();

            Item item10 = Item.builder()
                    .store(store1)
                    .name("드립커피")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c2.jpg")
                    .price(4000)
                    .build();

            Item item11 = Item.builder()
                    .store(store1)
                    .name("에스프레소")
                    .category(ItemCategory.COFFEE)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/c1.jpg")
                    .price(4000)
                    .build();

            Items items = new Items(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11);

            store1.updateCollection(storeOperationHours, storeImages, reviews, discounts, items);

            em.persist(store1);
            OwnerStore ownerStore = OwnerStore.builder()
                    .store(store1)
                    .owner(owner)
                    .build();
            owner.addOwnerStores(ownerStore);
        }

        public void dbStores5() throws ParseException {
            Store store1 = Store.builder()
                    .name("서브웨이")
                    .storeTodayDiscountThumbnail("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/t4.png")
                    .createdDate(LocalDateTime.now())
                    .outletNum(0)
                    .closedDay(StoreDay.SUN)
                    .address(new Address("충북 청주시 서원구 1순환로 676", "h2", "h3", "h4"))
                    .category(StoreCategory.FOOD)
                    .location((Point) new WKTReader().read(String.format("POINT(%s %s)", 129.455449, 34.625160)))
                    .phone("0507-1411-7580")
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
                    .storeImg("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a2.png")
                    .build();
            StoreImage storeImage2 = StoreImage.builder()
                    .store(store1)
                    .creatTime(LocalDateTime.now())
                    .storeImg("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/a3.png")
                    .build();
            StoreImages storeImages = new StoreImages(storeImage1, storeImage2);

            ReviewDetail reviewDetail1 = ReviewDetail.GREAT_COFFEE;

            ReviewDetail reviewDetail2 = ReviewDetail.GREAT_BEVERAGE;

            ReviewDetail reviewDetail3 = ReviewDetail.GREAT_FOOD;

            ReviewDetail reviewDetail4 = ReviewDetail.MANY_OUTLET;


            List<ReviewDetail> reviewDetails1 = new ArrayList<>();
            reviewDetails1.add(reviewDetail1);
            reviewDetails1.add(reviewDetail2);
            reviewDetails1.add(reviewDetail3);
            reviewDetails1.add(reviewDetail4);

            List<ReviewDetail> reviewDetails2 = new ArrayList<>();
            reviewDetails2.add(reviewDetail4);

            List<ReviewDetail> reviewDetails3 = new ArrayList<>();
            reviewDetails3.add(reviewDetail1);

            Review review1 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .reviewDetails(reviewDetails1)
                    .build();

            Review review2 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .reviewDetails(reviewDetails2)
                    .build();

            Review review3 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .reviewDetails(reviewDetails3)
                    .build();

            Reviews reviews = new Reviews(review1, review2, review3);

            Discount discount1 = Discount.builder()
                    .discountDay(DiscountDay.FRI)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discount discount2 = Discount.builder()
                    .discountDay(DiscountDay.FRI)
                    .discountRatio(10)
                    .people(4)
                    .store(store1)
                    .build();

            Discount discount3 = Discount.builder()
                    .discountDay(DiscountDay.MON)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discount discount4 = Discount.builder()
                    .discountDay(DiscountDay.WED)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discount discount5 = Discount.builder()
                    .discountDay(DiscountDay.TUE)
                    .discountRatio(10)
                    .people(4)
                    .store(store1)
                    .build();

            Discount discount6 = Discount.builder()
                    .discountDay(DiscountDay.THU)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discounts discounts = new Discounts(discount1, discount2, discount3, discount4, discount5, discount6);
//            em.persist(storeImage1);
//            em.persist(storeImage2);
//            em.persist(storeOperationHour1);

            Item item1 = Item.builder()
                    .store(store1)
                    .name("BLT")
                    .category(ItemCategory.PORK)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/z3.jpg")
                    .price(12000)
                    .build();

            Item item2 = Item.builder()
                    .store(store1)
                    .name("치킨데리야끼")
                    .category(ItemCategory.PORK)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/z4.jpg")
                    .price(10000)
                    .build();

            Item item3 = Item.builder()
                    .store(store1)
                    .name("에그마요")
                    .category(ItemCategory.PORK)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/z5.jpg")
                    .price(9000)
                    .build();

            Item item4 = Item.builder()
                    .store(store1)
                    .name("스테이크앤치즈")
                    .category(ItemCategory.PORK)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/z6.jpg")
                    .price(8000)
                    .build();

            Item item5 = Item.builder()
                    .store(store1)
                    .name("풀드포크바비큐")
                    .category(ItemCategory.PORK)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/z7.jpg")
                    .price(7000)
                    .build();

            Item item6 = Item.builder()
                    .store(store1)
                    .name("바비큐치킨")
                    .category(ItemCategory.PORK)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/z8.jpg")
                    .price(10000)
                    .build();

            Item item7 = Item.builder()
                    .store(store1)
                    .name("쉬림프")
                    .category(ItemCategory.PORK)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/z9.jpg")
                    .price(7000)
                    .build();

            Item item8 = Item.builder()
                    .store(store1)
                    .name("스파이시이탈리안")
                    .category(ItemCategory.PORK)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/z10.jpg")
                    .price(11000)
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
            Items items = new Items(item1, item2, item3, item4, item5, item6, item7, item8);

            store1.updateCollection(storeOperationHours, storeImages, reviews, discounts, items);
            em.persist(store1);
            OwnerStore ownerStore = OwnerStore.builder()
                    .store(store1)
                    .owner(owner)
                    .build();
            owner.addOwnerStores(ownerStore);
        }

        public void dbStores6() throws ParseException {
            Store store1 = Store.builder()
                    .name("황궁짜장")
                    .storeTodayDiscountThumbnail("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/th1.jpg")
                    .createdDate(LocalDateTime.now())
                    .outletNum(0)
                    .closedDay(StoreDay.SAT)
                    .address(new Address("충북 청주시 서원구 1순환로694번길 4 1층", "h2", "h3", "h4"))
                    .category(StoreCategory.FOOD)
                    .location((Point) new WKTReader().read(String.format("POINT(%s %s)", 127.455449, 36.623165)))
                    .phone("0507-1396-4655")
                    .paymentType(StorePaymentType.PREPAID)
                    .salesType(StoreSalesType.HALL)
                    .description("신속한 배달!!!")
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
                    .storeImg("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/y1.jpg")
                    .build();
            StoreImage storeImage2 = StoreImage.builder()
                    .store(store1)
                    .creatTime(LocalDateTime.now())
                    .storeImg("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/y2.jpg")
                    .build();
            StoreImages storeImages = new StoreImages(storeImage1, storeImage2);

            ReviewDetail reviewDetail1 = ReviewDetail.GREAT_COFFEE;

            ReviewDetail reviewDetail2 = ReviewDetail.GREAT_BEVERAGE;

            ReviewDetail reviewDetail3 = ReviewDetail.GREAT_FOOD;

            ReviewDetail reviewDetail4 = ReviewDetail.MANY_OUTLET;


            List<ReviewDetail> reviewDetails1 = new ArrayList<>();
            reviewDetails1.add(reviewDetail2);

            List<ReviewDetail> reviewDetails2 = new ArrayList<>();
            reviewDetails2.add(reviewDetail2);
            reviewDetails2.add(reviewDetail4);

            List<ReviewDetail> reviewDetails3 = new ArrayList<>();
            reviewDetails3.add(reviewDetail1);
            reviewDetails3.add(reviewDetail2);
            reviewDetails3.add(reviewDetail4);

            Review review1 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .reviewDetails(reviewDetails1)
                    .build();

            Review review2 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .reviewDetails(reviewDetails2)

                    .build();

            Review review3 = Review.builder()
                    .store(store1)
                    .createdDate(LocalDateTime.now())
                    .reviewDetails(reviewDetails3)

                    .build();

            Reviews reviews = new Reviews(review1, review2, review3);

            Discount discount1 = Discount.builder()
                    .discountDay(DiscountDay.TUE)
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
                    .discountDay(DiscountDay.FRI)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discount discount4 = Discount.builder()
                    .discountDay(DiscountDay.WED)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discount discount5 = Discount.builder()
                    .discountDay(DiscountDay.SUN)
                    .discountRatio(10)
                    .people(4)
                    .store(store1)
                    .build();

            Discount discount6 = Discount.builder()
                    .discountDay(DiscountDay.THU)
                    .discountRatio(5)
                    .people(2)
                    .store(store1)
                    .build();

            Discounts discounts = new Discounts(discount1, discount2, discount3, discount4, discount5, discount6);
//            em.persist(storeImage1);
//            em.persist(storeImage2);
//            em.persist(storeOperationHour1);

            Item item1 = Item.builder()
                    .store(store1)
                    .name("짜장면")
                    .category(ItemCategory.PORK)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/z1.jpg")
                    .price(7000)
                    .build();

            Item item2 = Item.builder()
                    .store(store1)
                    .name("짬뽕")
                    .category(ItemCategory.PORK)
                    .img("https://ootdzip.s3.ap-northeast-2.amazonaws.com/potatoimg/z2.jpg")
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
                    .name("곱빼기")
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
            OwnerStore ownerStore = OwnerStore.builder()
                    .store(store1)
                    .owner(owner)
                    .build();
            owner.addOwnerStores(ownerStore);
            em.persist(owner);
        }
    }
}
