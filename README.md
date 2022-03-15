# WebPOS

The demo shows a simple POS system in MVC architecture, which replaces the shell interface in aw02 with a pos web ui (https://github.com/bshbsh404/simple-pos-ui
).

![](screenshot.png)

To run

```shell
mvn clean spring-boot:run
```

Currently, it just lists the products for sale with a cart with one item (just for demonstration). 

Please read the tutorial at  https://www.baeldung.com/spring-boot-crud-thymeleaf and make the POS system robust and fully functional. You can also refer to other articles, for instance https://www.baeldung.com/tag/thymeleaf/ .



And please elaborate your understanding in MVC architecture via this homework in your README.md.

# MVC

## model

Products information is stored in class `PosInMemoryDB`. Cart information is stored in class `Cart` and `item`.

## view

`index.html` shows user the view of cart and products. Buttons in this page are bind with controller module.

## controller

class `PosController` functions as controller. It responds to `GET`/`REQUEST` when buttons in `index.html` are clicked, and then passes the arguments to class `PosServiceImp`.

## tour of user's click

```
user clicks 'add' button of 'iPhone 13' in index.html
a 'GET' is generated and sent to PosController
PosController::addProduct("PD4", 1)
PosServiceImpl::addProduct("PD4", 1)
PosInMemoryDB::getProduct("PD4")
Cart::getCart().addItem(new Item(iPhone13, 1))
```