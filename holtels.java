import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

class hotels {
    private static LinkedList<hotel> hotel = new LinkedList<>();
    public void create() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nMa hoa don (0: Theo gio - 1: Theo Ngay - Khac: Huy): ");
        int type = scanner.nextInt();
        if (type != 0 && type != 1) {
            return;
        }
        System.out.print("Ten khach hang: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Ngay thue (dd-MM-yyyy): ");
        String strDate = scanner.nextLine();
        while (!isDate(strDate)) {
            System.out.print("Sai dinh dang ngay. Vui long nhap lai!\nNgay thue (dd/MM/yyyy): ");
            strDate = scanner.nextLine();
        }
        System.out.print("So "+(type == 0 ? "ngay" : "gio")+" thue: ");
        int time = scanner.nextInt();
        System.out.print("Ma phong: ");
        int room_id = scanner.nextInt();
        while (room_id < 0 || room_id_Exist(room_id)) {
            System.out.print("Ma phong da ton tai hoac dinh dang sai. Vui long nhap lai!\nMa phong: ");
            room_id = scanner.nextInt();
        }
        System.out.print("Nhap gia phong: ");
        int price = scanner.nextInt();
        LocalDate date = convert_string_to_date(strDate);

        this.hotel.addLast(new hotel(type, date, name, room_id, price, time));
        System.out.print("Them thanh cong !");
    }

    public void show() {
        if (this.hotel.size() == 0) {
            System.out.println("\nChua co hoa don nao !");
            return;
        }

        System.out.println("\n===   DANH SACH HOA DON   ===\nMa hoa don\tTen khach hang\tMa phong\tNgay vao\tThoi gian thue\tGia phong\tTong tien\n");
        for (hotel hotel2 : hotel) {
            System.out.print(hotel2.set_get_Type(-1)+"\t"+hotel2.set_get_Name("")+"\t"+hotel2.set_get_Room_id(-1)+"\t"+hotel2.get_string_from_Date()+"\t"+hotel2.set_get_Time(-1)+"\t"+hotel2.set_get_Price(-1)+"\t"+hotel2.getTotalMoney()+"\n");
        }
    }

    public void showTotal() {
        if (this.hotel.size() == 0) {
            System.out.println("\nChua co hoa don nao !");
            return;
        }
        int price_0 = 0;
        int price_1 = 0;
        for (hotel hotel2 : hotel) {
            if (hotel2.set_get_Type(-1) == 0)
                price_0 += hotel2.getTotalMoney();
            else price_1 += hotel2.getTotalMoney();
        }
        System.out.println("\nPhong theo gio: "+price_0 +"\nPhong theo ngay: "+price_1);
    }

    public void getTotalInMonth() {
        if (this.hotel.size() == 0) {
            System.out.println("\nChua co hoa don nao !");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nNhap thang: ");
        int month = scanner.nextInt();
        if (month < 1 || month > 12) {
            System.out.print("\nNhap sai.");
            return;
        }

        System.out.print("\nNhap nam: ");
        int year = scanner.nextInt();
        if (year < 0) {
            System.out.print("\nNhap sai.");
            return;
        }

        int total = 0;

        for (hotel hotel2 : hotel) {
            if (hotel2.get_Date().getMonth().getValue() == month  && hotel2.get_Date().getYear() == year)
                total += hotel2.getTotalMoney();
        }

        System.out.println("\nTong so tien: "+total);
    }

    public boolean room_id_Exist(int room_id) {
        for (hotel hotel2 : hotel) {
            if (hotel2.set_get_Room_id(-1) == room_id) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDate(String paramString){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        LocalDate date = LocalDate.parse(paramString, formatter);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static LocalDate convert_string_to_date(String paramString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(paramString, formatter);
    }
}

class hotel {
    private int type;
    private LocalDate date;
    private String name;
    private int room_id;
    private int price;
    private int time;

    public hotel(int type, LocalDate date, String name, int room_id, int price, int time) {
        this.type = type;
        this.date = date;
        this.name = name;
        this.room_id = room_id;
        this.price = price;
        this.time = time;
    }

    public int set_get_Type(int type) {
        if (type == -1) {
            return this.type;
        }
        this.type = type;
        return type;
    }
    
    public void set_Date(LocalDate date) {
        this.date = date;
    }

    public LocalDate get_Date() {
        return date;
    }

    public String set_get_Name(String name) {
        if (name == "") {
            return this.name;
        }
        this.name = name;
        return name;
    }

    public int set_get_Room_id(int room_id) {
        if (room_id == -1) {
            return this.room_id;
        }
        this.room_id = room_id;
        return room_id;
    }

    public int set_get_Price(int price) {
        if (price == -1) {
            return this.price;
        }
        this.price = price;
        return price;
    }

    public int set_get_Time(int time) {
        if (time == -1) {
            return this.time;
        }
        this.time = time;
        return time;
    }

    public int getTotalMoney() {
        if (this.type == 0) {
            if (this.time <= 24) {
                return this.price * this.time;
            }
            else if (this.time > 24 && this.time < 30) {
                return 24 * this.price;
            }
            else if (this.time > 30) {
                if (this.time/24 > 7) {
                    return (int)((this.price * 7) + (this.price * (this.time/24 - 7)*0.8));
                }
                return (int)(this.price * this.time/24);
            }
        }
        if (this.time > 7) {
            return (int)((this.price * 7) + (this.price * (this.time - 7)*0.8));
        }
        return this.price * this.time;
    }

    public String get_string_from_Date() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.date.format(dateFormatter);
    }
}
