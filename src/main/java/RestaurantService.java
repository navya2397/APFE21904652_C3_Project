import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {
        List<Restaurant> allRestaurants = getRestaurants();
        for (Restaurant restaurant : allRestaurants) {
            if(restaurantName.equals(restaurant.getName()))
                return restaurant;
        }throw new restaurantNotFoundException(restaurantName);
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public int orderTotalFromCart(List<String> items,Restaurant restaurant){
        int total = 0;
        List<Item> menuItems = restaurant.getMenu();
        for(String itemName : items){
            for(Item menuItem : menuItems){
                if(itemName.equals(menuItem.getName())){
                    total += menuItem.getPrice();
                }
            }
        }
        return total;
    }

}
