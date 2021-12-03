package racingcar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;

public class RacingGame {
    private static final String MSG_INPUT_CAR_NAMES = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String MSG_INPUT_ATTEMPTS_NUM = "시도할 회수는 몇회인가요?";
    private static final String MSG_PRINT_EXECUTE_RESULT = "실행 결과";
    private static final String MSG_PRINT_WINNER = "최종 우승자 : ";

    private final List<Car> cars;
    private int maxPosition;

    public RacingGame() {
        this.cars = new ArrayList<>();
        this.maxPosition = 0;
    }

    public void play() {
        this.createCarObjByName(this.inputCarNames());
        int numberOfAttempts = this.inputNumberOfAttempts();
        System.out.println(MSG_PRINT_EXECUTE_RESULT);
        while (numberOfAttempts-- > 0) {
            for (Car car : this.cars) {
                car.moveForward();
                this.printEachAttempts(car.getName(), car.getPosition());
                this.updateMaxPosition(car.getPosition());
            }
            System.out.println();
        }
        this.printWinner();
    }

    private void printWinner() {
        StringBuilder sb = new StringBuilder();
        sb.append(MSG_PRINT_WINNER);
        for (Car car : this.cars) {
            if (car.getPosition() == this.maxPosition) {
                sb.append(car.getName());
                sb.append(", ");
            }
        }
        System.out.println(sb.substring(0, sb.length()-2));
    }

    private void updateMaxPosition(int position) {
        this.maxPosition = Math.max(this.maxPosition, position);
    }

    private void printEachAttempts(String name, int position) {
        System.out.print(name + " : ");
        while (position-- > 0) {
            System.out.print("-");
        }
        System.out.println();
    }

    private void createCarObjByName(List<String> carNameList) {
        for (String name : carNameList) {
            this.cars.add(new Car(name));
        }
    }

    // TODO: 입력에 대한 예외처리 하기
    private List<String> inputCarNames() {
        System.out.println(MSG_INPUT_CAR_NAMES);
        return Arrays.asList(Console.readLine().split(","));
    }

    private int inputNumberOfAttempts() {
        System.out.println(MSG_INPUT_ATTEMPTS_NUM);
        return Integer.parseInt(Console.readLine());
    }
}
