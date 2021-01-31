//問題１

let numbers = [2,5,12,13,15,18,22];

function isEven(num) {
    console.log(num + 'は偶数です');
}

for(let i = 0; i< numbers.length; i++){
    if(numbers[i] % 2 === 0){
        isEven(numbers[i]);
    }
}

//問題２

class car {
    constructor(gass,number){
        this.gass = gass;
        this.number = number;
    }

    getNumGas(){
        console.log(`ガソリンは${this.gass}です。ナンバーは${this.number}です。`);
    }
    
}

let mycar = new car(10,1245);
mycar.getNumGas();