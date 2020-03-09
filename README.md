# Cabify Mobile Challenge

Besides providing exceptional transportation services, Cabify also runs a physical store which sells Products.

Our list of products looks like this:

``` 
Code         | Name                |  Price
-------------------------------------------------
VOUCHER      | Cabify Voucher      |   5.00€
TSHIRT       | Cabify T-Shirt      |  20.00€
MUG          | Cabify Coffee Mug   |   7.50€
```

Various departments have insisted on the following discounts:

 * The marketing department believes in 2-for-1 promotions (buy two of the same product, get one free), and would like to have a 2-for-1 special on `VOUCHER` items.

 * The CFO insists that the best way to increase sales is with discounts on bulk purchases (buying x or more of a product, the price of that product is reduced), and demands that if you buy 3 or more `TSHIRT` items, the price per unit should be 19.00€.

Cabify's checkout process allows for items to be scanned in any order, and should return the total amount to be paid.

Examples:

    Items: VOUCHER, TSHIRT, MUG
    Total: 32.50€

    Items: VOUCHER, TSHIRT, VOUCHER
    Total: 25.00€

    Items: TSHIRT, TSHIRT, TSHIRT, VOUCHER, TSHIRT
    Total: 81.00€

    Items: VOUCHER, TSHIRT, VOUCHER, VOUCHER, MUG, TSHIRT, TSHIRT
    Total: 74.50€


# To do
- Implement an app where a user can pick products from a list and checkout them to get the resulting price. No need to implement any real payment system, just a fake feedback about the payment has been completed.
- The discounts can change in the future, depending on the year season we apply different ones.
- There is no need for a user login screen.
- We would like to show users what discounts have been applied in their purchase. 
- You should get the list of products from [here](https://api.myjson.com/bins/4bwec).

## Bonus
- The app should work also offline, and all that this implies. 

**The code should:**
- Be written as production-ready code. You will write production code. We would like you to build it in the same way as if you were going to publish to the store.
- Be easy to grow and easy to add new functionality.
- Have notes attached, explaning the solution and why certain things are included and others are left out.
- Be written either in Swift or Kotlin, preferably in the latest stable version of the language.

# About my solution
The app in a single module using clean architecture. Features are separated by package.

### Included features
* To apply discounts using promotions, I have used a strategy pattern, so we could create new promotions in the future.
* Products are reached from the provided URL and cached in a local data base, so the app is able to work offline.
* Products added to cart are also stored in the database, so we can recover our shopping session at any moment.
* Products and applied discounts are shown in the checkout screen.
* To simulate the payment feedback I have a used random boolean.
* Some test are included.

### Left out
* In the cart, I should allow the user to increase and decrease the amount of items.

