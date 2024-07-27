Дипломный проект  [![Build Status](https://travis-ci.org/angular/protractor.svg?branch=master)](https://travis-ci.org/angular/protractor) [![CircleCI Status](https://circleci.com/gh/angular/protractor.svg?style=shield)](https://circleci.com/gh/angular/protractor) [![Join the chat at https://gitter.im/angular/protractor](https://badges.gitter.im/angular/protractor.svg)](https://gitter.im/angular/protractor)
==========

[Protractor](http://angular.github.io/protractor) is an end-to-end test framework for [Angular](http://angular.io/) and [AngularJS](http://angularjs.org) applications. Protractor is a [Node.js](http://nodejs.org/) program built on top of [WebDriverJS](https://github.com/SeleniumHQ/selenium/wiki/WebDriverJs). Protractor runs tests against your application running in a real browser, interacting with it as a user would.

Compatibility
-------------

Protractor 5 is compatible with nodejs v6 and newer.

Protractor works with AngularJS versions greater than 1.0.6/1.1.4, and is compatible with Angular applications. Note that for Angular apps, the `binding` and `model` locators are not supported. We recommend using `by.css`.


Getting Started
---------------
See the [Protractor Website](http://www.protractortest.org) for most documentation.

To get set up and running quickly:
- Work through the [Tutorial](http://www.protractortest.org/#/tutorial)
- See the [API](http://www.protractortest.org/#/api)

Once you are familiar with the tutorial, you’re ready to move on. To modify your environment, see the Protractor Setup docs. To start writing tests, see the Protractor Tests docs.

To better understand how Protractor works with the Selenium WebDriver and Selenium Server see the reference materials.


Getting Help
------------

Check the [Protractor FAQ](https://github.com/angular/protractor/blob/master/docs/faq.md) and read through the [Top 20 questions on StackOverflow](http://stackoverflow.com/questions/tagged/protractor?sort=votes&pageSize=20).

Please ask usage and debugging questions on [StackOverflow](http://stackoverflow.com/questions/tagged/protractor) (use the ["protractor"](http://stackoverflow.com/questions/ask?tags=protractor) tag), the [Gitter](https://gitter.im/angular/protractor) chat room, or in the [Angular discussion group](https://groups.google.com/forum/?fromgroups#!forum/angular). (Please do not ask support questions here on Github.)


For Contributors
----------------
See [DEVELOPER.md](https://github.com/angular/protractor/blob/master/DEVELOPER.md)1) Авторизация. Позитивный тест
2) Авторизация. Негативный тест
3) Добавить тренировку на сегодня (быстрое добавление, без заполнения полей)
    3.1 выбрать категорию - подкатегорию
    3.2 нажать "добавить тренировку"
    3.3 проверить добавление
4) Добавить тренировку на будущую дату (расширенное добавление с частичным заполнением информации по полям).
    4.1 выбрать категорию - подкатегорию
    4.2 заполнить информацию о тренировке (название, описание, чек-бокс запланированная дистанция)
    4.3 нажать "добавить тренировку"
    4.4 проверить добавление
5) добавить тренировку за прошедшую дату (с заполнением всех полей)
6) редактирование тренировки
    6.1 добавить тренировку без заполнения полей
    6.2 перейти в неё и заполнить результаты тренировки (пульс, время и т.д)
    6.3 проверка
Дополнительно:
7) проверка копирования тренировки
8) добавление комментария к тренировке
9) добавление тренировки открыв отображение на неделю
10) удаление тренировки
11) Калькулятор тренировок. позитивный тест на расчет - заполнить поля, получить результат
12) Калькулятор тренировок. негативный тест на расчет - оставить какое-то поле незаполненным - получить ошибку
13)"Остальные калькуляторы" - калькулятор темпа -позитивный тест на расчет
14)"Остальные калькуляторы" - калькулятор темпа -негативный тест на расчет
15) Просмотр отчёта за период -вкладка "отчёты и статистика"(с выбором категории или без)
16) "Экипировка и маршруты" - добавить обувь
Дополнительно:
   17)"Экипировка и маршруты" - добавить велосипед
18) Daily Vitals - добавление основных показателей - заполнить показателями за один день
19) Изменение настроек (настройки - редактирование профиля)
Дополнительно:
20)  Изменение настроек (настройки - изменение настроек)
21) Проверка успешного выхода из приложения