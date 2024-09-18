package Game_Of_Life;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GameOfLifeMain {
    public static void main(String[] args){
        System.out.println("Please Enter Number of Alive cell to proceed Further!");

        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        System.out.println("Enter cell X Y");
        Set<Cell> aliveCell = new HashSet<>();
        while(n>0){
            String s = sc.nextLine();
            String[] inputArray = s.split(" ");
            int x = Integer.parseInt(inputArray[0]);
            int y = Integer.parseInt(inputArray[1]);
            aliveCell.add(new Cell(x,y));
            n--;
        }


        Set<Cell> newAliveCell = getNextGenerationForAllCell(aliveCell);


       // System.out.println(aliveCell);
        for(Cell cell:newAliveCell){
            System.out.println(cell.x+" , "+ cell.y);
        }

    }

    private static Set<Cell> getNextGenerationForAllCell(Set<Cell> aliveCell) {

        Set<Cell> newAliveCell = new HashSet<>();

        Set<Cell> aliveCellIncludingItsAllNeighbour = new HashSet<>();
        
        for(Cell cell: aliveCell){
            aliveCellIncludingItsAllNeighbour.add(cell);
            aliveCellIncludingItsAllNeighbour.addAll(getNetCurrentCellNeibhour(cell));
        }

        for(Cell cell:aliveCellIncludingItsAllNeighbour){
            Set<Cell> neibhors = getNetCurrentCellNeibhour(cell);

            int countOfAliveNebhor=0;
            for (Cell neibhor : neibhors){
                if(aliveCell.contains(neibhor)){
                    countOfAliveNebhor+=1;
                }
            }

            if(aliveCell.contains(cell)){
                if(countOfAliveNebhor==2 || countOfAliveNebhor==3){
                    newAliveCell.add(cell);
                }
            }else{
                if(countOfAliveNebhor==3){
                    newAliveCell.add(cell);
                }
            }

        }



        return newAliveCell;
    }

    private static Set<Cell> getNetCurrentCellNeibhour(Cell cell) {

        Set<Cell> allNeibhourCurrentCell = new HashSet<>();
        //for each cell neibhour be like
//        nx,ny: -1 , -1
//        nx,ny: -1 , 0
//        nx,ny: -1 , 1
//        nx,ny: 0 , -1
//        nx,ny: 0 , 1
//        nx,ny: 1 , -1
//        nx,ny: 1 , 0
//        nx,ny: 1 , 1

        for(int nx=-1;nx<=1;nx++){
            for(int ny=-1;ny<=1;ny++){
                if(nx!=0 || ny!=0){
                    //System.out.println("nx,ny: "+nx+" , "+ny);
                    allNeibhourCurrentCell.add(new Cell(cell.x+nx, cell.y+ny));
                }
            }
        }

        return allNeibhourCurrentCell;

    }
}

//input
//6
//1 1
//1 2
//1 3
//2 2
//2 3
//2 4



