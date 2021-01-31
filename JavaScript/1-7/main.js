class taiyaki {
    constructor(nakami){
        this.nakami = nakami;
    }
    
    contents(){
        console.log(`中身は${this.nakami}です。`)
    }
}

let anko = new taiyaki("あんこ");
anko.contents();
let cream = new taiyaki("クリーム");
cream.contents();
let cheese = new taiyaki("チーズ");
cheese.contents();