package it.pippo.wisewordspro

sealed class NavigationItem(var route: String, val icon: Int, var title: String) {
    object Random : NavigationItem("Random", R.drawable.random_icon, "Random")
    object Grid : NavigationItem("Grid", R.drawable.grid_icon, "Grid")
    object List : NavigationItem("List", R.drawable.list_icon, "List")
    object Simple : NavigationItem("Simple", R.drawable.simple_icon, "Simple")
}