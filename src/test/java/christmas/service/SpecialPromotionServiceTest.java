package christmas.service;

import christmas.common.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class SpecialPromotionServiceTest {
    SpecialPromotionService service = new SpecialPromotionService();
    OrderStatementStub receipt = new OrderStatementStub();

    @BeforeEach
    void cleanUpBasicSuccess() {
        receipt.setReservationDay(25);
        receipt.setReturnPrice(Range.MIN_PRICE.getValue());
    }

    @ValueSource(ints = {3,10,24,25,31})
    @ParameterizedTest
    void 날짜적용_성공_일요일과_크리스마스(int today) {
        receipt.setReservationDay(today);

        assertThat(service.support(receipt)).isTrue();
    }

    @ValueSource(ints = {-10,0,2,4,32})
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
    void 조건적용_실패_범위미만(int price) {
        receipt.setReturnPrice(price);

        assertThat(service.support(receipt)).isFalse();
    }

    @Test
    void 할인적용_성공() {
        int result = 1000;

        assertThat(service.discount(receipt))
                .isEqualTo(result);
    }

}
