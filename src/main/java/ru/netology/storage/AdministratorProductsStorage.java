package ru.netology.storage;

import ru.netology.orders.Order;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AdministratorProductsStorage {
    private final Set<ProductsByCategory> productsByCategories = new HashSet<>();
    private Categories categories;

    public static void mainStorage() {

        AdministratorProductsStorage admin = new AdministratorProductsStorage();

        //создаем категории товаров
        admin.categories = new Categories(ListCategoriesStorage.getInstance()
                .add("Молоко")
                .add("Сыры")
                .add("Мясо")
                .add("Хлеб")
                .add("Масло")
                .add("Крупа"));

        //для каждой категории выделяем память
        for (String str : admin.categories.get()) {
            admin.productsByCategories.add(new ProductsByCategory(str, new ListProductsStorageAdapter()));
        }
        //заполняем наш склад товаром
        ProductsByCategory pc = admin.getObjectProductsByCategory("Молоко");
        pc.changeProduct(new Product("Агуша", 965, 0, "Агуша"), 100);
        pc.changeProduct(new Product("Домик в деревне 2.5%", 100, 0, "Вимм-Билль-Данн"), 200);
        pc.changeProduct(new Product("Минская марка 3.2%", 160, 0, "Минск"), 500);
        pc.changeProduct(new Product("Простоквашино 3.2%", 150, 0, "Смоленск"), 700);

        pc = admin.getObjectProductsByCategory("Сыры");
        pc.changeProduct(new Product("Адыгейский", 500, 0, "Москва"), 100);
        pc.changeProduct(new Product("Моцарелла", 800, 0, "Курск"), 200);
        pc.changeProduct(new Product("Гауда", 600, 0, "Минск"), 500);
        pc.changeProduct(new Product("Пошехонский", 500, 0, "Витебск"), 700);

        pc = admin.getObjectProductsByCategory("Мясо");
        pc.changeProduct(new Product("Индейка", 500, 0, "Москва"), 100);
        pc.changeProduct(new Product("Курица", 800, 0, "Курск"), 200);
        pc.changeProduct(new Product("Свинина", 600, 0, "Минск"), 500);
        pc.changeProduct(new Product("Баранина", 500, 0, "Витебск"), 700);

        pc = admin.getObjectProductsByCategory("Хлеб");
        pc.changeProduct(new Product("Бородинский", 320, 0, "Москва"), 100);
        pc.changeProduct(new Product("Ржаной", 295, 0, "Курск"), 200);
        pc.changeProduct(new Product("Пшеничный", 295, 0, "Минск"), 500);
        pc.changeProduct(new Product("Зерновой", 901, 0, "Витебск"), 700);

        pc = admin.getObjectProductsByCategory("Масло");
        pc.changeProduct(new Product("Оливковое 1л", 532, 0, "Италия"), 100);
        pc.changeProduct(new Product("Подсолнечное, Олейна 900мл", 547, 0, "Курск"), 200);
        pc.changeProduct(new Product("Подсолнечное, Слобода 1л", 650, 0, "Минск"), 500);
        pc.changeProduct(new Product("Подсолнечное, Золотая семечка", 995, 0, "Витебск"), 700);

        pc = admin.getObjectProductsByCategory("Крупа");
        pc.changeProduct(new Product("Гречневая, Увелка", 165, 0, "Краснодар"), 100);
        pc.changeProduct(new Product("Рис, Южная ночь", 157, 0, "Краснодар"), 200);
        pc.changeProduct(new Product("Овсяная крупа", 165, 0, "Минск"), 500);
        pc.changeProduct(new Product("Кукурузная", 107, 0, "Алтай"), 700);

        CustomerProductsStorage.getInstance().setCategories(admin.categories);
        CustomerProductsStorage.getInstance().setProductsByCategories(admin.productsByCategories);
    }

    private ProductsByCategory getObjectProductsByCategory(String category) {
        return productsByCategories.stream().filter(p -> category.equals(p.getCategory())).collect(Collectors.toList()).get(0);
    }

    public static void removingPurchasedProductsFromStore(Order order) {
        for (ProductsByCategory products : CustomerProductsStorage.getInstance().getProductsByCategories()) {
            for (String category : order.getListCategories()) {
                if (category.equals(products.getCategory())) {
                    for (Product product : products.getProducts()) {
                        if (order.getProducts().get(product) != null) {
                            int countStore = products.getValue(product).orElse(0);
                            int countOrder = order.getProducts().get(product);
                            int result = (countStore >= countOrder) ? countStore - countOrder : 0;
                            System.out.println("countStore = " + countStore + " - countOrder = " + countOrder + " - result = " + result);
                            products.changeProduct(product, result);
                            System.out.println("***************Склад**************");
                            System.out.println("Для товара " + product.getName());
                            System.out.println("Количество минус " + order.getProducts().get(product) + ", стало " + products.getValue(product).orElse(-1));
                        }
                    }
                }
            }
        }
    }
}
