let sports: string[] = ["Golf", "Cricket", "Tennis", "Swimming"];

console.log("------initial------");
sports.forEach(sport => console.log(sport));


console.log("------after grow------");
sports.push("Football", "Baseball");
sports.forEach(sport => console.log(sport));


