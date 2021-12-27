import Vue from 'vue'
import App from './App.vue'
import router from './router'
import VueRouter from "vue-router"
import store from './store'
import axios from "axios";

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

Vue.config.productionTip = false

Vue.use(ElementUI)

Vue.use(VueRouter)

Vue.prototype.$axios = axios;
axios.defaults.baseURL="";

Vue.prototype.$content="BBC;All BBC Titles;\n" +
    "Science Fiction & Fantasy;Science Fiction;Fantasy;\n" +
    "Computers & Accessories;Computer Accessories & Peripherals;\n" +
    "Blu-ray;Movies;TV;\n" +
    "Disney Home Video;All Disney Titles;By Age;Animated Cartoons;Live Action;Animated Characters;\n" +
    "Skin Care;Body;\n" +
    "Classic Rock;British Invasion;Arena Rock;Album-Oriented Rock (AOR);\n" +
    "Folk;Traditional Folk;\n" +
    "Camera & Photo;Accessories;\n" +
    "Metal;Pop Metal;British Metal;\n" +
    "Spanish Language;Comedia;Infantil y familiar;Acci??n y aventura;Ciencia ficci??n y fantas??a;Misterio y suspenso;Drama;\n" +
    "MTV;All MTV;Reality TV;Jackass;\n" +
    "HBO;All HBO Titles;\n" +
    "Power & Hand Tools;Power Tools;\n" +
    "Sewing;Trim & Embellishments;\n" +
    "Monty Python Store;Feature Films;\n" +
    "Independently Distributed;Documentary;Drama;Sports;Horror;Art House & International;Special Interests;Kids & Family;Comedy;Action & Adventure;Musicals & Performing Arts;Science Fiction & Fantasy;Music Video & Concerts;\n" +
    "The Twilight Zone;Twilight Zone DVDs;\n" +
    "Model & Hobby Building;Pinewood Derby;\n" +
    "Christian & Gospel;Gospel;Pop & Contemporary;Hard Rock & Metal;\n" +
    "PBS;NOVA;All;\n" +
    "James Bond;Collections & Documentaries;Roger Moore;Pierce Brosnan;Sean Connery;\n" +
    "Rock;Blues Rock;Oldies & Retro;\n" +
    "\"Painting; Drawing & Art Supplies\",Painting;\n" +
    "Mini-DVD;Television;\n" +
    "Widescreen;Comedy;Documentary;Action & Adventure;Drama;Horror;Kids & Family;Sci-Fi & Fantasy;Art House & International;Mystery & Suspense;Westerns;Musicals & Performing Arts;\n" +
    "Crafting;Craft Supplies;\n" +
    "International Music;Far East & Asia;\n" +
    "Special Interest;Instructional;\n" +
    "Paramount Home Entertainment;Comedy;DreamWorks;Drama;Classics;Action & Adventure;Boxed Sets;\n" +
    "CBS News Network;60 Minutes Store;\n" +
    "Characters & Series;Scooby Doo;Wall-E;Mary-Kate & Ashley;Harry Potter;Shrek;\n" +
    "R&B;Soul;\n" +
    "Pop;Dance Pop;Adult Contemporary;Vocal Pop;Adult Alternative;Singer-Songwriters;Tributes;Easy Listening;\n" +
    "Musicals & Performing Arts;Musicals;Ballet & Dance;Classical;Documentary;Opera;Independently Distributed;\n" +
    "Studio Specials;Sony Pictures Home Entertainment;Warner Home Video;Lionsgate Home Entertainment;Universal Studios Home Entertainment;Lions Gate Home Entertainment;MGM Home Entertainment;20th Century Fox Home Entertainment;Sci Fi Channel;Miramax Home Entertainment;Wellspring Home Video;New Yorker Films;Miramax Home Video;Focus Features;DreamWorks;Sundance Channel Home Entertainment;Sundance Channel Store;ABC TV Shows;\n" +
    "Dance & Electronic;Electronica;\n" +
    "Health Care;First Aid;Smoking Cessation;\n" +
    "Replacement Parts;Brake System;\n" +
    "John Wayne Store;All Titles;\n" +
    "Jane Austen on DVD Store;All Titles;\n" +
    "Men;Clothing;\n" +
    "Criterion Collection;All;Essential Art House;\n" +
    "Holidays & Seasonal;Christmas;\n" +
    "Walt Disney Studios Home Entertainment;Live Action;All Disney Titles;Disney Channel;Walt Disney Legacy Collection;Walt Disney Treasures;Animated Movies;\n" +
    "Country;Bluegrass;\n" +
    "Fully Loaded DVDs;DTS;Special Editions;New Line Platinum Series;Infinifilm Edition;Extended Editions;Two-Disc Special Editions;Ultimate Editions;Vista Series;\n" +
    "Holiday & Wedding;Christmas;\n" +
    "Genre for Featured Categories;Faith & Spirituality;Action & Adventure;Documentary;Special Interests;Music Videos & Concerts;Kids & Family;Drama;Comedy;Sports;Mystery & Thrillers;Horror;Westerns;Exercise & Fitness;Foreign Films;Military & War;Anime & Manga;Romance;Musicals;Science Fiction;TV Talk Shows;LGBT;Fantasy;Reality TV;Performing Arts;TV Game Shows;TV News Programming;\n" +
    "Broadway;Broadway Theatre Archive;Musicals;\n" +
    "Terminator;All Terminator;\n" +
    "African American Cinema;Comedy;Drama;TV & Miniseries;Breakthrough Cinema;\n" +
    "Boxed Sets;Mystery & Suspense;Television;Military & War;Documentary;Comedy;Art House & International;Classics;Drama;Westerns;Musicals & Performing Arts;Sci-Fi & Fantasy;Horror;Special Interests;Kids & Family;Action & Adventure;Fitness & Yoga;Anime;Music Video & Concerts;Sports;\n" +
    "Diet & Sports Nutrition;Weight Loss;\n" +
    "Art House & International;By Country;By Original Language;By Genre;\n" +
    "Christian Video;Bible;General;Christmas;Biography;Animation;Easter;Music;Feature Films;Jesus;\n" +
    "Shakespeare on DVD Store;The Works;More to Explore;Shakespeare 101;Playing Shakespeare;\n" +
    "Accessories;\"Maintenance, Upkeep & Repairs\";\n" +
    "FX;All FX Shows;\n" +
    "ABC News;ABC News Specials;20/20;ABC News Classics;Primetime;Nightline;\n" +
    "Docurama;Politics;History;Sports;African American Heritage;Film History & Film Making;Military & War;Art & Artists;LGBT;Crime & Conspiracy;Music & Performing Arts;Science & Technology;Comedy;Nature & Wildlife;\n" +
    "Indie & Alternative;Alternative Rock;\n" +
    "Latin Music;Latin Pop;\n" +
    "Showtime;All Showtime Titles;\n" +
    "Classics;Silent Films;\n" +
    "Sci-Fi Series & Sequels;Godzilla;Planet of the Apes;X-Men;Men in Black;\n" +
    "Hair Care;Hair Coloring Products;\n" +
    "Travel Channel;Passport to Europe;\n" +
    "Office & School Supplies;\"Forms, Recordkeeping & Money Handling\";\"Calendars, Planners & Personal Organizers\";\n" +
    "First to Know;Movies;Television;Blu-ray;\n" +
    "Music Artists;\"Manilow, Barry\";Eminem;\"Jackson, Michael\";\"King, B.B.\";The Beatles;\"Shakur, Tupac\";Carpenters;\"Cooper, Alice\";\"Nelson, Willie\";Bon Jovi;\"Armstrong, Louis\";\"Cash, Johnny\";Roxy Music;\"Haggard, Merle\";\"Gillespie, Dizzy\";\"Presley, Elvis\";Queen;\"Ellington, Duke\";\"McLachlan, Sarah\";\"Hendrix, Jimi\";\"Emerson, Lake & Palmer\";\"Estefan, Gloria\";\"Rock, Chris\";\"Marley, Bob\";N Sync;Godsmack;\"Collins, Phil\";\"Borge, Victor\";Sting;\"Denver, John\";\"Clapton, Eric\";\"Coltrane, John\";\"John, Elton\";\"Carey, Mariah\";\"Johnson, Jack\";Pink Floyd;\"Dylan, Bob\";Madonna;Bee Gees;Genesis;\"Aguilera, Christina\";\"Osbourne, Ozzy\";\"Rich, Buddy\";\"Twain, Shania\";\"Davis, Miles\";\"Rieu, Andre\";Yes;Aerosmith;The Doors;ABBA;Rush;\"Bowie, David\";Grateful Dead;\"Kelly, R.\";KISS;\"Basie, Count\";Snoop Dogg;\"Blakey, Art\";\"Waters, Muddy\";Weird Al;The Judds;\"Turner, Tina\";\"Young, Neil\";Deep Purple;AC/DC;Santana;\"Brooks, Garth\";Celtic Woman;Fleetwood Mac;\"Jones, George\";\"Holiday, Billie\";Eagles;\"Springsteen, Bruce\";Riverdance;\"Matthews, Dave\";\"Metheny, Pat\";The Rolling Stones;Beastie Boys;Metallica;\"Vaughan, Stevie Ray\";\"Crow, Sheryl\";Roy Orbison;\"Johnson, Robert\";Tool;\"Streisand, Barbra\";Prince;Iron Maiden;\"Spears, Britney\";Il Divo;Backstreet Boys;\"Valli, Frankie\";\"King, Carole\";\n" +
    "PC;Games;\n" +
    "The Comedy Central Store;Comedy Central Roast;Other;Comedy Central Presents;Stand Up;Politics;\n" +
    "MGM Home Entertainment;All MGM Titles;\n" +
    "A&E Home Video;All A&E Titles;A&E Original Movies;Biography;Major League Baseball;\n" +
    "Sexual Wellness;Safer Sex;\n" +
    "Dogs;\"Collars, Harnesses & Leashes\";\n" +
    "Discovery Channel;Channels;\n" +
    "Animation;Feature Films;By Animator;Stop-Motion & Clay Animation;Computer Animation;\n" +
    "Classical;Symphonies;Chamber Music;\n" +
    "Cult Movies;Drama;International;Comedy;Horror;Sci-Fi & Fantasy;Full Moon Video;Music & Musicals;Action & Adventure;Blaxploitation;Psychedelic;Camp;Landmark Cult Classics;Exploitation;Prison;Subversive Cinema;\n" +
    "Made-for-TV Movies;All Made-for-TV Movies;Lifetime Original Movies;\n" +
    "Broadway & Vocalists;Musicals;\n" +
    "Jewish Heritage;Holocaust;"

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
