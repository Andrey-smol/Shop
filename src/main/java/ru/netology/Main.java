package ru.netology;

import ru.netology.address.Address;
import ru.netology.address.AddressBuilder;
import ru.netology.customers.Customer;
import ru.netology.delivery.DeliveryOrders;
import ru.netology.delivery.DeliveryOrdersStorage;
import ru.netology.orders.Emmiter;
import ru.netology.orders.Order;
import ru.netology.storage.*;

import java.util.*;

public class Main {
    private enum STATUS {STATUS_OK, STATUS_RETURN, STATUS_CATEGORY, STATUS_ERROR}

    private static int number;
    private static Scanner in;
    private static List<Product> list;
    private static final String GOODBYE = "До свидания";
    private static final String SELECT_CATEGORY = "Кат";
    private static final String SELECT_RETURN = "Выход";
    private static final String SELECT_SHOPPING_CART = "Корзина";

    public static void main(String[] args) {
        AdministratorProductsStorage.mainStorage();

        Emmiter emmiter = new Emmiter();
        DeliveryOrders deliveryOrders = new DeliveryOrders(emmiter, DeliveryOrdersStorage.getInstance());
        StorageOrders storageOrders = new StorageOrders(emmiter, StoreOrdersStorage.getInstance());

        in = new Scanner(System.in);
        Order order = new Order();
        while (true) {
            showMainMenu();
            //ввод категории
            String str = in.nextLine();
            if (exitCheck(str)) {
                break;
            }
            if (getCategories().contains(str)) {
                list = showProductsByCategory(str);
            } else {
                System.out.println("вы ввели - " + str + " - такой категории нет, повторите ввод");
                continue;
            }
            //выбор продуктов в выбранной категории
            STATUS status = selectProductDialog(order);
            if (status == STATUS.STATUS_RETURN) {
                break;
            }
            if (status == STATUS.STATUS_CATEGORY) {
                continue;
            }

            System.out.println("Вы закончили заказ, в вашей корзине: ");
            order.getProducts().forEach((product, integer) -> {
                System.out.println("Имя продукта: " + product.getName());
                System.out.println("Количество: " + integer);
                System.out.println("*****************************");
            });
            System.out.println("На сумму: " + order.getPrice());

            status = selectStepForRegistration();

            if (status == STATUS.STATUS_RETURN) {
                break;
            }
            if (status == STATUS.STATUS_CATEGORY) {
                continue;
            }

            setCustomerDetails(order);
            emmiter.sendData(order);
            break;
        }
        System.out.println(GOODBYE);
    }

    private static STATUS selectStepForRegistration() {
        while (true) {
            System.out.println("Для возврата в выбор продуктов введите " + SELECT_CATEGORY);
            System.out.println("Для выхода без регистрации заказа введите " + SELECT_RETURN);
            System.out.println("Для входа в корзину выбранных товаров и регистрации заказа введите " + SELECT_SHOPPING_CART);
            String str = in.nextLine();
            if (SELECT_RETURN.equalsIgnoreCase(str)) {
                return STATUS.STATUS_RETURN;
            }
            if (SELECT_CATEGORY.equalsIgnoreCase(str)) {
                return STATUS.STATUS_CATEGORY;
            }
            if (SELECT_SHOPPING_CART.equalsIgnoreCase(str)) {
                return STATUS.STATUS_OK;
            }
        }
    }

    private static void setCustomerDetails(Order order) {
        System.out.println("Для окончания регистрации введите свои данные");
        System.out.print("Введите ваше имя: ");
        String name = in.nextLine();
        System.out.print("Введите вашу фамилию: ");
        String surname = in.nextLine();
        System.out.println("Адрес доставки");
        System.out.print("Город: ");
        String city = in.nextLine();
        System.out.print("Улица: ");
        String street = in.nextLine();
        System.out.print("Дом: ");
        String house = in.nextLine();
        System.out.print("Квартира: ");
        String apartment = in.nextLine();
        System.out.print("Телефон для связи, в формате (ххх-хх-хх): ");
        String phone = in.nextLine();
        Address address = new AddressBuilder().setCity(city)
                .setStreet(street)
                .setHouse(house)
                .setApartment(apartment)
                .setPhone("+7" + phone)
                .build();
        order.setCustomer(new Customer(name, surname, address));
    }

    private static STATUS selectProductDialog(Order order) {
        while (true) {
            System.out.println("Для выхода в выбор категории введите " + SELECT_CATEGORY);
            System.out.println("Для входа в корзину выбранных товаров и регистрации заказа введите " + SELECT_SHOPPING_CART);
            System.out.print("Для выбора товара введите номер товара: ");
            String num = in.nextLine();
            if (exitCheck(num)) {
                return STATUS.STATUS_RETURN;
            }
            if (SELECT_CATEGORY.equalsIgnoreCase(num)) {
                return STATUS.STATUS_CATEGORY;
            }
            if (SELECT_SHOPPING_CART.equalsIgnoreCase(num)) {
                return STATUS.STATUS_OK;
            }
            System.out.print("Введите количество: ");
            String count = in.nextLine();
            if (exitCheck(count)) {
                return STATUS.STATUS_RETURN;
            }
            if (Integer.parseInt(num) < list.size()) {
                System.out.println("***************************************************************************");
                System.out.println("Вы выбрали: " + list.get(Integer.parseInt(num)).getName());
                order.addProduct(list.get(Integer.parseInt(num)), Integer.parseInt(count));
                order.setPrice(order.getPrice() + list.get(Integer.parseInt(num)).getPrice() * Integer.parseInt(count));
                System.out.println("Ваша корзина стоит: " + order.getPrice());
            } else {
                System.out.println("Вы ввели не корректное число, повторите ввод");
            }
        }
    }

    private static boolean exitCheck(String str) {
        if (SELECT_RETURN.equalsIgnoreCase(str)) {
            return true;
        }
        return false;
    }

    private static List<Product> showProductsByCategory(String str) {
        List<Product> list = new ArrayList<>();
        number = 0;
        ProductsByCategory products = (ProductsByCategory) CustomerProductsStorage.getInstance().getProductsByCategories()
                .stream()
                .filter(p -> p.getCategory().equals(str))
                .toArray()[0];
        for (Product p : products.getProducts()) {
            System.out.println(number++ + " - " + p.getName() + ", цена: " + p.getPrice() + " руб, производитель: " + p.getManufacturer());
            OptionalInt i = products.getValue(p);
            System.out.println("Количество на складе: " + (i.isPresent() ? i.getAsInt() : "товара нет"));
            System.out.println("Рейтинг: " + p.getRating());
            System.out.println("***************************************************************************");
            list.add(p);
        }
        return list;
    }

    private static void showMainMenu() {
        System.out.println("***************************************************************************");
        System.out.println("Вас приветствует наш магазин");
        System.out.println("Вы можете выбрать нужную категорию товара и товар в категории");
        System.out.println();
        getCategories().stream().forEach(c -> System.out.print(" || " + c + " || "));

        System.out.println();
        System.out.println("Для выбора товара выберите нужную категорию");
        System.out.println("Для выхода из магазина наберите " + SELECT_RETURN);
        System.out.print("Введите название категории: ");
    }

    private static Set<String> getCategories() {
        return CustomerProductsStorage.getInstance().getCategories().get();
    }

}