/**
 * Represents enum class for all vending items in the machine
 *
 * @author ashah420
 * @version 1.00
 */
public enum VendingItem {
    Water            (30.0),
    Furs             (250.0),
    Food             (100.0),
    Ore              (350),
    Games            (250),
    FireArms         (1250),
    Medicine         (650),
    Machines         (900),
    Narcotics        (3500),
    Robots           (5000),
    Rishabh          (Integer.MAX_VALUE),
    travel1           (100),
    travel2           (100),
    travel3           (100),
    travel4           (100),
    travel5           (100);

    private final double price;

    /**
     * Constructs a vendingitem with price element
     *
     * @param price the price of the vendingitem
     */
    VendingItem(double price) {
        this.price = price;
    }

    /**
     * Retrieves the price of the vendingitem
     *
     * @return the price of the vendingitem
     */
    public double getPrice() {
        return price;
    }

    /**
     * Constructs a string of the vending item and the price
     *
     * @return string representation of vending item
     */
    public String toString() {
        return String.format("%s%2.2f", this.name() + ":" + " $",
                         this.getPrice());
    }

     public static final String[] SolarNames = {
            "Acamar",
            "Adahn",        // The alternate personality for The Nameless One in "Planescape: Torment"
            "Aldea",
            "Andevian",  // brains -zombie who i never met
            "Antedi",    
            "Balosnee",
            "Baratas",
            "Brax",         // One of the heroes in Master of Magic
            "Bretel",       // This is a Dutch device for keeping your pants up.
            "Calondia",
            "Campor",
            "Capelle",      // The city I lived in while programming this game
            "Carzon",
            "Castor",       // A Greek demi-god
            "Cestus",
            "Cheron",
            "Courteney",    // After Courteney Cox…
            "Daled",
            "Damast",
            "Davlos",
            "Deneb",
            "Deneva",
            "Devidia",
            "Draylon",
            "Drema",
            "Endor",
            "Esmee",        // One of the witches in Pratchett's Discworld
            "Exo",
            "Ferris",       // Iron
            "Festen",       // A great Scandinavian movie
            "Fourmi",       // An ant, in French
            "Frolix",       // A solar system in one of Philip K. Dick's novels
            "Gemulon",
            "Guinifer",     // One way of writing the name of king Arthur's wife
            "Hades",        // The underworld
            "Hamlet",       // From Shakespeare
            "Helena",       // Of Troy
            "Hulst",        // A Dutch plant
            "Iodine",       // An element
            "Iralius",
            "Janus",        // A seldom encountered Dutch boy's name
            "Japori",
            "Jarada",
            "Jason",        // A Greek hero
            "Kaylon",
            "Khefka",
            "Kira",         // My dog's name
            "Klaatu",       // From a classic SF movie
            "Klaestron",
            "Korma",        // An Indian sauce
            "Kravat",       // Interesting spelling of the French word for "tie"
            "Krios",
            "Laertes",      // A king in a Greek tragedy
            "Largo",
            "Lave",         // The starting system in Elite
            "Ligon",
            "Lowry",        // The name of the "hero" in Terry Gilliam's "Brazil"
            "Magrat",       // The second of the witches in Pratchett's Discworld
            "Malcoria",
            "Melina",
            "Mentar",       // The Psilon home system in Master of Orion
            "Merik",
            "Mintaka",
            "Montor",       // A city in Ultima III and Ultima VII part 2
            "Mordan",
            "Myrthe",       // The name of my daughter
            "Nelvana",
            "Nix",          // An interesting spelling of a word meaning "nothing" in Dutch
            "Nyle",         // An interesting spelling of the great river
            "Odet",
            "Og",           // The last of the witches in Pratchett's Discworld
            "Omega",        // The end of it all
            "Omphalos",     // Greek for navel
            "Orias",
            "Othello",      // From Shakespeare
            "Parade",       // This word means the same in Dutch and in English
            "Penthara",
            "Picard",       // The enigmatic captain from ST:TNG
            "Pollux",       // Brother of Castor
            "Quator",
            "Rakhar",
            "Ran",          // A film by Akira Kurosawa
            "Regulas",
            "Relva",
            "Rhymus",
            "Rochani",
            "Rubicum",      // The river Ceasar crossed to get into Rome
            "Rutia",
            "Sarpeidon",
            "Sefalla",
            "Seltrice",
            "Sigma",
            "Sol",          // That's our own solar system
            "Somari",
            "Stakoron",
            "Styris",
            "Talani",
            "Tamus",
            "Tantalos",     // A king from a Greek tragedy
            "Tanuga",
            "Tarchannen",
            "Terosa",
            "Thera",        // A seldom encountered Dutch girl's name
            "Titan",        // The largest moon of Jupiter
            "Torin",        // A hero from Master of Magic
            "Triacus",
            "Turkana",
            "Tyrus",
            "Umberlee",     // A god from AD&D, which has a prominent role in Baldur's Gate
            "Utopia",       // The ultimate goal
            "Vadera",
            "Vagra",
            "Vandor",
            "Ventax",
            "Xenon",
            "Xerxes",       // A Greek hero
            "Yew",          // A city which is in almost all of the Ultima games
            "Yojimbo",      // A film by Akira Kurosawa
            "Zalkon",
            "Zuul"          // From the first Ghostbusters movie
    };
}