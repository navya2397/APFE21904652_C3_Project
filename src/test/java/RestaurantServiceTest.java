import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalTime;
import java.util.*;

class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;


    @BeforeEach
    public void init() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }


    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        Restaurant result = service.findRestaurantByName("Amelie's cafe");
        Assertions.assertEquals(restaurant.getName(),result.getName());
    }

    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        assertThrows(restaurantNotFoundException.class,()->service.findRestaurantByName("Pantry d'or"));
    }

    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }

    @Test
    public void show_total_of_order_when_list_is_greater_than_1(){
        List<String> cartItems = new ArrayList<String>();        ;
        cartItems.add("Vegetable lasagne");
        cartItems.add("Sweet corn soup");
        int totalAmount = service.orderTotalFromCart(cartItems,restaurant);
        assertEquals(388,totalAmount);
    }

    @Test
    public void show_rate_of_selected_item_when_list_is_equal_than_1(){
        List<String> items = new ArrayList<String>();
        items.add("Vegetable lasagne");
        int result = service.orderTotalFromCart(items, restaurant);
        assertEquals(269,result);
    }

    @Test
    public void show_total_when_no_item_selected_should_return_0() {
        List<String> items = new ArrayList<String>();
        int result = service.orderTotalFromCart(items, restaurant);
        assertEquals(0,result);
    }



}