package christmas.service;

import christmas.common.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class ChristmasDDayPromotionServiceTest {
    ChristmasDDayPromotionService service;
    OrderStatementStub receipt;

    public ChristmasDDayPromotionServiceTest() {
        service = new ChristmasDDayPromotionService();
        receipt = new OrderStatementStub();
    }

    @BeforeEach
    void cleanUp() {
        receipt.setReservationDay(24);
        receipt.setReturnPrice(Range.MIN_PRICE.getValue());
    }

    @ValueSource(ints = {1,2,10,20,24,25})
    @ParameterizedTest
    void 날짜적용_성공_1일부터_25일까지(int today) {
        receipt.setReservationDay(today);

        assertThat(service.support(receipt)).isTrue();
    }

    @ValueSource(ints = {0,26,27,30,31,40})
    @ParameterizedTest
    void 날짜적용_실패_범위초과(int today) {
        receipt.setReservationDay(today);

        assertThat(service.support(receipt)).isFalse();
    }

    @ValueSource(ints = {10000, 12000, 240000})
    @ParameterizedTest
    void 조건적용_성공_최소금액도달(int price) {
        receipt.setReturnPrice(price);

        assertThat(service.support(receipt)).isTrue();
    }

    @ValueSource(ints = {0,-10,9999})
    @ParameterizedTest
    void 조건적용_실패_최소금액미만(int price) {
        receipt.setReturnPrice(price);

        assertThat(service.support(receipt)).isFalse();
    }

    @ValueSource(ints = {1,6,11,25})
    @ParameterizedTest
    void 할인적용_성공(int today) {
        receipt.setReservationDay(today);
        service.support(receipt);
        int result = 1000 + (100 * (today - 1));

        assertThat(service.discount(receipt))
                .isEqualTo(result);
    }

}
