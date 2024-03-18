package study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    private static final String plus = "+";
    private static final String minus = "-";
    private static final String multiple = "*";
    private static final String divide = "/";

    public void main(){
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();

        System.out.println(calculator(value));
    }

    @DisplayName("문자열을 받아 계산을 한다.")
    @Test
    void calculator(){
        String str = "2 + 3 * 4 / 2";
        assertThat(calculator(str)).isEqualTo(10);
    }

    @DisplayName("문자열을 받아 공백으로 쪼갠다.")
    @Test
    void splitStr(){
        assertThat(splitStr("2 + 3 * 4 / 2")).containsExactly("2","+","3","*","4","/","2");
    }

    @DisplayName("a와 b를 계산한다.")
    @Test
    void calculate(){
        assertThat(calculate(1,2,"+")).isEqualTo(3);
    }

    @DisplayName("a와 b를 계산 시 존재하지 않는 사칙연산이면 예외를 발생시킨다.")
    @Test
    void calculateThrowException(){
        assertThatThrownBy(() -> calculate(1,2,"s"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("존재하지 않는 사칙연산입니다.");
    }

    @DisplayName("문자열을 정수로 치환하다.")
    @Test
    void parseInt(){
        assertThat(parseInt("1")).isEqualTo(1);
    }

    @DisplayName("문자열을 정수로 치환할 시 정수가 아니면 예외를 발생 시킨다.")
    @Test
    void parseIntThrowException(){
        assertThatThrownBy(() -> parseInt("+"))
            .isInstanceOf(NumberFormatException.class);
    }

    @DisplayName("플러스를 체크한다.")
    @ParameterizedTest
    @CsvSource(value = {"+:true", "-:false", "*:false", "/:false"}, delimiter = ':')
    void isPlus(String input, boolean expected) {
        assertEquals(isPlus(input), expected);
    }

    @DisplayName("마이너스를 체크한다.")
    @ParameterizedTest
    @CsvSource(value = {"+:false", "-:true", "*:false", "/:false"}, delimiter = ':')
    void isMinus(String input, boolean expected) {
        assertEquals(isMinus(input), expected);
    }

    @DisplayName("곱셈을 체크한다.")
    @ParameterizedTest
    @CsvSource(value = {"+:false", "-:false", "*:true", "/:false"}, delimiter = ':')
    void isMultiple(String input, boolean expected) {
        assertEquals(isMultiple(input), expected);
    }

    @DisplayName("나누기를 체크한다.")
    @ParameterizedTest
    @CsvSource(value = {"+:false", "-:false", "*:false", "/:true"}, delimiter = ':')
    void isDivide(String input, boolean expected) {
        assertEquals(isDivide(input), expected);
    }

    private int calculator(String str){
        String[] strArr = splitStr(str);
        int a = parseInt(strArr[0]);
        for(int i=2; i<strArr.length; i=i+2){
            a = calculate(a, parseInt(strArr[i]), strArr[i-1]);
        }
        return a;
    }

    private int calculate(int a, int b, String regex){
        if(isPlus(regex)) return a + b;
        if(isMinus(regex)) return a - b;
        if(isMultiple(regex)) return a * b;
        if(isDivide(regex)) return a / b;
        throw new IllegalArgumentException("존재하지 않는 사칙연산입니다.");
    }

    private int parseInt(String str){
        return Integer.parseInt(str);
    }

    private String[] splitStr(String str){
        return str.split(" ");
    }

    private boolean isPlus(String regex){
        return plus.equals(regex);
    }

    private boolean isMinus(String regex){
        return minus.equals(regex);
    }

    private boolean isMultiple(String regex){
        return multiple.equals(regex);
    }

    private boolean isDivide(String regex){
        return divide.equals(regex);
    }
}
