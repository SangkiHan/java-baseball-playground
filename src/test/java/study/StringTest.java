package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StringTest {

    @DisplayName("문자열 대체를 테스트한다.")
    @Test
    void replace() {
        String actual = "abc".replace("b", "d");
        assertThat(actual).isEqualTo("adc");
    }

    @DisplayName("문자열 분리를 테스트 한다.")
    @Test
    void split(){
        String str = "1,2";
        String[] strArr = str.split(",");
        assertThat(strArr).contains("1");
        assertThat(strArr).containsExactly("1","2");
    }

    @DisplayName("문자열 자르기를 테스트한다.")
    @Test
    void substring(){
        String str = "(1,2)";
        str = str.substring(1,4);
        assertThat(str).isEqualTo("1,2");
    }

    @DisplayName("문자열 문자를 가져온다.")
    @Test
    void charAt(){
        String str = "abc";
        assertThat(str.charAt(0)).isEqualTo('a');
    }

    @DisplayName("문자열 문자를 가져온다 범위가 밖이면 StringIndexOutOfBoundsException이 발생한다.")
    @Test
    void charAtThrowStringIndexOutOfBoundsException(){
        assertThatThrownBy(() -> "abc".charAt(3))
            .isInstanceOf(StringIndexOutOfBoundsException.class);
    }
}
