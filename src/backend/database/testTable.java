package backend.database;

public class testTable {
    //Add first basic table here

    public int vehicle_id;
    public String user_firstname;
    public String user_lastname;
    public String permit_type;
    public int parking_cost;

    //Reference to a Schedule class could be created to store their schedule (for our algorithms)
    public String user_role;
    public Boolean has_permit;

    /**
     * Create new "blank" testTable
     */
    public testTable(){
        vehicle_id = 0;
        user_firstname = "";
        user_lastname = null;
        permit_type = "";
        parking_cost = 0;
        //        user_schedule = null;
        user_role = null;
        has_permit = false;

    }

    /**
     * Create new testTable with specified inputs
     */
    public testTable(int v, String f, String L, String p, int c, String r, Boolean h){
        vehicle_id = v;
        user_firstname = f;
        user_lastname = L;
        permit_type = p;
        parking_cost = c;
        //        user_schedule = null;
        user_role = r;
        has_permit = h;

    }



}
 

