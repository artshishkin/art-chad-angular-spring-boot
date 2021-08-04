var sports = ["Golf", "Cricket", "Tennis", "Swimming"];
console.log("------fori------");
for (var i = 0; i < sports.length; i++) {
    console.log(sports[i]);
}
console.log("------forin------");
for (var sportsKey in sports) {
    console.log("sportsKey: " + sportsKey + "; sports[sportsKey]: " + sports[sportsKey]);
}
console.log("------forof------");
for (var _i = 0, sports_1 = sports; _i < sports_1.length; _i++) {
    var sport = sports_1[_i];
    if (sport === "Cricket")
        console.log(sport + "<<Favourite");
    else
        console.log(sport);
}
console.log("------forEach------");
sports.forEach(function (sport) { return console.log(sport); });
