#include <iostream>
#include <fstream>
#include <vector>
#include <iostream>
#include <fstream>
#include <math.h>

    int main(void){
        int level;
        int health;
        int endurance;
        int baseAttack = 0;
        int xp = 0;
        std::cout << "************************" << std::endl;
        std::cout << "* Fast key choose level*" << std::endl;
        std::cout << "* -1 = 1001            *" << std::endl;
        std::cout << "************************" << std::endl;
        std::cin >> level;
        if(level == -1){
            level = 1001;
        }
        std::ofstream file;
        file.open("resultSwordMan.txt");

        for(int i= 0; i<level; i++){

            health = (((i*i) + (i * 2) )+ 10*(i* 0.3));
            endurance = ((i * 1.1) + 10* (2.5* i));
            baseAttack = (((i*2)*0.75)+(i*i-(i*1.4))+ i*0.25)+0.5;
            xp = ((i+(1*i)*0.8)*17);

            std::cout << "Level:" << i << "   | Health:"<< health << "  | Endurance:" << endurance<< " |  Base Attack:" << baseAttack << "  | xp:"<< xp << std::endl;
            file << "Level:" << i << "  |Health:" << health << "  |Endurance:" << endurance << "  |Base Attack:" << baseAttack << "  | xp:" << xp << ".\n";
        }
        file.close();

    return 0;

}
