import {SwimmingCoach} from "./SwimmingCoach";
import {FootballCoach} from "./FootballCoach";
import {Coach} from "./Coach";

let swimmingCoach = new SwimmingCoach();
let footballCoach = new FootballCoach();

let coaches: Coach[] = [swimmingCoach, footballCoach];

for (const coach of coaches) {
    console.log(coach.getDailyWorkout());
}

