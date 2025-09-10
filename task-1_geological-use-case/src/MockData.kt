var mockMineral = listOf(
    Mineral("Amethyst", Luster.VITREOUSGLASSY, Color.PURPLE, Hardness(7.0), Fracture.CONCHOIDAL),
    Mineral("Garnet", Luster.VITREOUSRESINOUS, Color.VARYING, Hardness(6.5, 7.5), Fracture.CONCHOIDAL),
    Mineral("Kyanite", Luster.VITREOUSPEARLY, Color.BLUE, Hardness(5.0, 7.0), Fracture.SPLINTERY),
    Mineral("Cinnabar", Luster.ADAMINTINE, Color.RED, Hardness(2.5), Fracture.UNEVEN),
    Mineral("Diamond", Luster.ADAMINTINE, Color.VARYING, Hardness(10.0), Fracture.CONCHOIDAL),
    Mineral("Gypsum", Luster.VITREOUSPEARLYSILKY, Color.VARYING, Hardness(7.0), Fracture.FIBROUS),
    Mineral("Copper", Luster.METALLIC, Color.COLORLESS, Hardness(2.5, 3.0), Fracture.HACKLY),
    Mineral("Muscovite", Luster.VITREOUSPEARLYSILKY, Color.COLORLESS, Hardness(2.0, 2.5), Fracture.UNEVEN),
    Mineral("Aragonite", Luster.VITREOUS, Color.WHITEYELLOW, Hardness(3.5, 4.0), Fracture.SUBCONCHOIDAL),
    Mineral("Talc", Luster.PEARLYGREASY, Color.WHITEGREEN, Hardness(1.0), Fracture.UNEVEN),
)

var mockLocation = listOf(
    Location("Cairo", "A bit sandy",GeoPoint(30.0444, 31.2357)),   // Egypt
    Location("Oslo", "Surrounded by fjords and forests", GeoPoint(59.9139, 10.7522)),   // Norway
    Location("Reykjavik", "Volcanic landscapes and hot springs", GeoPoint(64.1355, -21.8954)),  // Iceland),
    Location("Sydney", "Coastal city with sandstone geology", GeoPoint(-33.8688, 151.2093)), // Australia
    Location("Santiago", "At the foothills of the Andes", GeoPoint(-33.4489, -70.6693)) // Chile
)