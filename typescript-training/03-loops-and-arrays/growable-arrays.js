var sports = ["Golf", "Cricket", "Tennis", "Swimming"];
console.log("------initial------");
sports.forEach(function (sport) { return console.log(sport); });
console.log("------after grow------");
sports.push("Football", "Baseball");
sports.forEach(function (sport) { return console.log(sport); });
