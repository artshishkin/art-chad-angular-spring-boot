let sports: string[] = ["Golf", "Cricket", "Tennis", "Swimming"];

console.log("------fori------");
for (let i = 0; i < sports.length; i++) {
    console.log(sports[i]);
}

console.log("------forin------");
for (const sportsKey in sports) {
    console.log(`sportsKey: ${sportsKey}; sports[sportsKey]: ${sports[sportsKey]}`);
}

console.log("------forof------");
for (const sport of sports) {
    if (sport === "Cricket")
        console.log(sport + "<<Favourite");
    else
        console.log(sport);
}
console.log("------forEach------");
sports.forEach(sport => console.log(sport));


