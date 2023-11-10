package christmas.service;

import christmas.common.Range;
import christmas.entity.MainDish;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class WeekendPromotionServiceTest {
    WeekendPromotionService service = new WeekendPromotionService();
    OrderStatementStub receipt = new OrderStatementStub();

    @ValueSource(ints = {3,7,11,27,31})
    @ParameterizedTest
    void 날짜적용_성공_금_토요일(int today) {
        assertThat(service.support(today)).isTrue();
    }

    @ValueSource(ints = {-9,0,1,8,23,30,32,40})
    @ParameterizedTest
    void 날짜적용_실패_범위초과(int today) {
        assertThat(service.support(today)).isFalse();
    }

    @ValueSource(ints = {10000, 12000, 240000})
    @ParameterizedTest
    void 조건적용_성공_최소금액도달_조건에맞는음식(int price) {
        receipt.setReturnPrice(price);
        receipt.addDish(MainDish.BBQ_RIBS, 1);

        assertThat(service.support(receipt)).isTrue();
    }

    @ValueSource(ints = {0,-10,9999})
    @ParameterizedTest
    void 조건적용_실패_최소금액미만(int price) {
        receipt.setReturnPrice(price);
        receipt.addDish(MainDish.BBQ_RIBS, 1);

        assertThat(service.support(receipt)).isFalse();
    }

    @Test
    void 조건적용_실패_조건에맞는음식없음() {
        receipt.setReturnPrice(Range.MIN_PRICE.getValue());

        assertThat(service.support(receipt)).isFalse();
    }

    @Test
    void 할인적용_성공_단일품목() {
        receipt.setReturnPrice(Range.MIN_PRICE.getValue());
        receipt.addDish(MainDish.BBQ_RIBS, 1);
        int result = 2_023;

        assertThat(service.discount(receipt))
                .isEqualTo(result);
    }

    @Test
    void 할인적용_성공_단일품목_여러개() {
        receipt.setReturnPrice(Range.MIN_PRICE.getValue());
        receipt.addDish(MainDish.T_BONE_STEAK, 10);
        int result = 20_230;

        assertThat(service.discount(receipt))
                .isEqualTo(result);
    }

    @Test
    void 할인적용_성공_다중품목_여러개() {
        receipt.setReturnPrice(Range.MIN_PRICE.getValue());
        receipt.addDish(MainDish.BBQ_RIBS, 2);
        receipt.addDish(MainDish.T_BONE_STEAK, 2);
        receipt.addDish(MainDish.CHRISTMAS_PASTA, 3);
        receipt.addDish(MainDish.SEAFOOD_PASTA, 3);
        int result = 20_230;

        assertThat(service.discount(receipt))
                .isEqualTo(result);
    }
}