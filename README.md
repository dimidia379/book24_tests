# Автотесты для [book24.ru](https://book24.ru)

## Cписок проверок:

##UI

- [x] В заголовке страницы товара содержится название товара
- [x] На странице товара присутствует название товара
- [x] Кнопка добавления в корзину на странице товара меняет текст на 'Оформить заказ' после добавления товара в корзину
- [x] В заголовке страницы корзины должно содержаться 'Корзина'
- [x] Отображение наименования товара в корзине
- [x] Увеличение количества товара в корзине
- [x] Уменьшение количества товара в корзине
- [x] Удаление товара из корзины

##API

- [x] Фильтр книг по издательству выдает книги только этого издательства
- [x] Фильтр книг по печати по требованию выдает только книги, печатаемые по требованию
- [x] В списке предложений, выдаваемых при вводе поискового запроса, присутствует соответствующий запросу товар
- [x] Товар добавляется в корзину

# Стек технологий
![Java](readmeImages/Java.png)
![Gradle](readmeImages/Gradle.png)
![JUnit5](readmeImages/JUnit5.png)
![Selenide](readmeImages/Selenide.png)
![RestAssured](readmeImages/RestAssured.png)
![AllureReport](readmeImages/AllureReport.png)
![Github](readmeImages/Github.png)
![AllureTestOps](readmeImages/AllureTestOps.png)
![Jenkins](readmeImages/Jenkins.png)
![Selenoid](readmeImages/Selenoid.png)
![Jira](readmeImages/Jira.png)
![Telegram](readmeImages/Telegram.png)


## Для запусков автотестов используется Jenkins.

##### Пример готовой сборки можно посмотреть [по ссылке](https://jenkins.autotests.cloud/job/09-Julia_Zvereva-lesson23/)

### Параметры запуска в Jenkins
![Jenkins_params](readmeImages/Jenkins_params.png)

### Статистика запусков в Jenkins
![Jenkins_statistic](readmeImages/Jenkins_statistic.png)

### Отчёт о прохождении автотестов в Allure Report
![Allure_report](readmeImages/Allure_report.png)

### Список автотестов в Allure Report
![Allure_suite](readmeImages/Allure_suite.png)

### Хранение тестовой документации в Allure TestOps
![Testops](readmeImages/screenshots/Testops_cases.png)

### Прохождение тестов в Allure TestOps
![Testops](readmeImages/screenshots/Testops.png)

### Уведомления о прохождении автотестов в Telegram
![Telegram](readmeImages/screenshots/Telegram.png)

### Видео о прохождении тестов
![video](readmeImages/screenshots/vid.gif)

### Для запуска локально
```
gradle clean test
```

### Для запуска удаленно
```bash
clean
test
-Dbrowser=${BROWSER}
-DbrowserVersion=${BROWSER_VERSION}
-DbrowserSize=${BROWSER_SIZE}
-DbrowserMobileView="${BROWSER_MOBILE}"
-DremoteDriverUrl=${REMOTE_DRIVER_URL}
-DvideoStorage=https://${REMOTE_DRIVER_URL}/video/
-Dthreads=${THREADS}
```
