//問題１
let scores = [10,15,20,25];
console.log(scores);
for (let i = 0; i < scores.length; i++){
    if(scores[i] % 2 ===0){
        console.log(scores[i] + "は偶数です");
    }
}

//問題２

let car = {gass:20.5,num:1234};
console.log(car);
console.log("ガソリンは"+ car.gass + "です");
console.log("ナンバーは"+ car.num + "です");