package ru.netology;

import ru.netology.address.Address;
import ru.netology.address.AddressBuilder;
import ru.netology.address.CheckCityDelivery;
import ru.netology.customers.Customer;
import ru.netology.delivery.DeliveryOrders;
import ru.netology.delivery.DeliveryOrdersStorage;
import ru.netology.orders.Emmiter;
import ru.netology.orders.Order;
import ru.netology.storage.*;

import java.util.*;

public class Main {
    private enum STATUS {STATUS_OK, STATUS_RETURN, STATUS_CATEGORY, STATUS_ERROR}

    private static Scanner in;
    private static List<Product> listProducts;
    private static final String GOODBYE = "До свидания";
    private static final String SELECT_CATEGORY = "Категории";
    private static final String SELECT_RETURN = "Выход";
    private static final String SELECT_SHOPPING_CART = "Корзина";
    private static final String RETURN_SELECT_PRODUCTS = "Дальше";

    public static void main(String[] args) {
        AdministratorProductsStorage.mainStorage();

        in = new Scanner(System.in);
        Order order = new Order();
        while (true) {
            showMainMenu();
            //ввод категории
            String category = in.nextLine();
            if (SELECT_RETURN.equalsIgnoreCase(category)) {
                break;
            }
            if (getCategories().contains(category)) {
                listProducts = showProductsByCategory(category);
            } else {
                System.out.println("вы ввели - " + category + " - такой категории нет, повторите ввод");
                continue;
            }
            //выбор продуктов в выбранной категории
            STATUS status = selectProductDialog(order, category);
            if (status == STATUS.STATUS_RETURN) {
                break;
            }
            if (status == STATUS.STATUS_CATEGORY) {
                continue;
            }

            System.out.println("В вашей корзине: ");
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
            Emmiter.getInstance().sendData(order);
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
        while (true) {
            System.out.println("Для окончания регистрации введите свои данные");
            System.out.print("Введите ваше имя: ");
            String name = in.nextLine();
            System.out.print("Введите вашу фамилию: ");
            String surname = in.nextLine();

            System.out.println("Список городов доставки товаров");
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            CheckCityDelivery.getInstance().getiCheckCityDeliveryStorage().getCities().forEach(System.out::println);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println("Адрес доставки");
            System.out.print("Город: ");
            String city = in.nextLine();
            if (!CheckCityDelivery.getInstance().getiCheckCityDeliveryStorage().checkList(city)) {
                System.out.println("Вы ввели город в который нет поставки");
                continue;
            }
            System.out.print("Улица: ");
            String street = in.nextLine();
            System.out.print("Дом: ");
            String house = in.nextLine();
            System.out.print("Квартира: ");
            String apartment = in.nextLine();
            System.out.print("Телефон для связи, в формате (xxx ххх-хх-хх): ");
            //to do check phone
            String phone = in.nextLine();
            System.out.print("Дата доставки в формате час - день - месяц (хх-хх-хх): ");
            String date = in.nextLine();
            //to do check date
            Address address = new AddressBuilder()
                    .setCity(city)
                    .setStreet(street)
                    .setHouse(house)
                    .setApartment(apartment)
                    .setPhone("+7" + phone)
                    .build();
            order.setCustomer(new Customer(name, surname, address));
            order.setDateDelivery(date);
            break;
        }
    }

    private static STATUS selectProductDialog(Order order, String category) {
        while (true) {
            System.out.println("**********************************************************************");
            System.out.println("Для выхода из магазина наберите " + SELECT_RETURN);
            System.out.println("Для выхода в выбор категории введите " + SELECT_CATEGORY);
            System.out.println("Для выхода из выбора продуктов в этой категории введите " + RETURN_SELECT_PRODUCTS);
            System.out.print("Для выбора товара введите номер товара: ");
            String num = in.nextLine();
            if (SELECT_RETURN.equalsIgnoreCase(num)) {
                return STATUS.STATUS_RETURN;
            }
            if (SELECT_CATEGORY.equalsIgnoreCase(num)) {
                return STATUS.STATUS_CATEGORY;
            }
            if (RETURN_SELECT_PRODUCTS.equalsIgnoreCase(num)) {
                return STATUS.STATUS_OK;
            }
            System.out.print("Введите количество: ");
            String count = in.nextLine();
            if (SELECT_RETURN.equalsIgnoreCase(count)) {
                return STATUS.STATUS_RETURN;
            }
            if (!isNumeric(num) || !isNumeric(count)) {
                System.out.println("Не корректный ввод данных, повторите.");
                continue;
            }
            int n = Integer.parseInt(num);
            int c = Integer.parseInt(count);
            if (n < listProducts.size()) {
                Product product = listProducts.get(n);
                System.out.println("***************************************************************************");
                System.out.println("Вы выбрали: " + product.getName());

                //количество данного продукта на складе
                OptionalInt count_ = CustomerProductsStorage.getInstance().numberProductByStore(category, product);
                if (!count_.isPresent()) {
                    System.out.println("Данного продукта нет на складе");
                    continue;
                }
                if (count_.getAsInt() < c) {
                    System.out.println("Вы выбрали количество товара больше чем на складе, повторите ввод");
                    continue;
                }
                order.addProduct(product, c);
                order.addCategory(category);
                order.setPrice(order.getPrice() + product.getPrice() * c);
                System.out.println("Ваша корзина стоит: " + order.getPrice());
            } else {
                System.out.println("Вы ввели не корректное число, повторите ввод");
            }
        }
    }

    private static List<Product> showProductsByCategory(String category) {
        List<Product> list = new ArrayList<>();
        int number = 0;
        Optional<ProductsByCategory> op = CustomerProductsStorage.getInstance().getObjectProductsByCategory(category);
        if (!op.isPresent()) {
            System.out.println("Для данной категории нет продуктов на складе");
        } else {
            System.out.println("***************Категория " + category + "***************");
            ProductsByCategory products = op.get();
            for (Product p : products.getProductsStorage().get()) {
                System.out.println(number++ + " - " + p.getName() + ", цена: " + p.getPrice() + " руб, производитель: " + p.getManufacturer());
                OptionalInt i = products.getProductsStorage().getValue(p);
                System.out.println("Количество на складе: " + (i.isPresent() ? i.getAsInt() : "товара нет"));
                System.out.println("Рейтинг: " + p.getRating());
                System.out.println("***************************************************************************");
                list.add(p);
            }
        }
        return list;
    }

    private static void showMainMenu() {
        System.out.println("***************************************************************************");
        System.out.println("Вас приветствует наш магазин");
        System.out.println("Вы можете выбрать нужную категорию товара и товар в категории");
        System.out.println();
        getCategories().forEach(c -> System.out.print(" || " + c + " || "));

        System.out.println();
        System.out.println("Для выхода из магазина наберите " + SELECT_RETURN);
        System.out.println("Для выбора товара выберите нужную категорию");
        System.out.print("Введите название категории: ");
    }

    private static Set<String> getCategories() {
        return CustomerProductsStorage.getInstance().getCategories().getCategoriesStorage().get();
    }


    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}

