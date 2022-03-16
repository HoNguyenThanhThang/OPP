import java.util.Date;
import java.util.Scanner;

public class App extends hotels {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        hotels hotels = new hotels();
        int select = 1;
        while (select > 0 && select < 5) {
            show2();
            select = scanner.nextInt();
            if (select == 1) {
                hotels.create();
            }
            if (select == 2) {
                hotels.show();
            }
            if (select == 3) {
                hotels.showTotal();
            }
            if (select == 4) {
                hotels.getTotalInMonth();
            }
        }
    }

    public static void show2() {
        System.out.print("\n\n-----------------------------\n");
        System.out.print("1 : Them hoa don\n");
        System.out.print("2 : Xem hoa don\n");
        System.out.print("3 : Xem tong hoa don theo loai\n");
        System.out.print("4 : Tinh trung binh thanh tien trong thang\n");
        System.out.print("Khac : Huy\n\n");
        System.out.print("Chon: ");
    }
    
}
