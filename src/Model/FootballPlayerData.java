package Model;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FootballPlayerData implements TableData, Displayable, sortable, Searchable {

    private ArrayList<TableMember> players;
    private ArrayList<TableMember> clonedPlayers;
    private int firstLineToDisplay;
    private int highlightedLine;
    private int lastLineToDisplay;
    private int linesBeingDisplayed;
    private int sortField = -1;
    private String searchTerm;
    private int tableMemberindex;
    private boolean searchResult;
    private int fieldIndex;

    Map<String, FootballPlayer> numberMap = new HashMap<>();
    Map<String, FootballPlayer> positionMap = new HashMap<>();
    Map<String, FootballPlayer> nameMap = new HashMap<>();
    Map<String, FootballPlayer> heightMap = new HashMap<>();
    Map<String, FootballPlayer> weightMap = new HashMap<>();
    Map<String, FootballPlayer> hometwonMap = new HashMap<>();
    Map<String, FootballPlayer> highschoolMap = new HashMap<>();

    public FootballPlayerData() {

        players = new ArrayList<>();
        clonedPlayers = new ArrayList<>();

        loadTable();
        this.setLinesBeingDisplayed(10);
        this.setFirstLineToDisplay(0);
        this.setLastLineToDisplay(9);

        generateMap();
    }

    //------------------------------------------------------------------------------------------------------------
    @Override
    public void loadTable() {
        ReadPlayersFromXML();
    }

    public void ReadPlayersFromXML() {
        try {
            FootballPlayer fp;
            XMLDecoder decoder;

            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("FootballPlayerTable.xml")));
            fp = new FootballPlayer();
            while (fp != null) {
                try {

                    fp = (FootballPlayer) decoder.readObject();
                    players.add(fp);
                    clonedPlayers.add(fp);

                } catch (ArrayIndexOutOfBoundsException theend) {

                    //System.out.println("end of file");
                    break;
                }
            }
            decoder.close();
        } catch (Exception xx) {
            xx.printStackTrace();

        }
    }

    @Override
    public ArrayList<TableMember> getTable() {
        return this.players;
    }

    @Override
    public void setTable(ArrayList<TableMember> players) {
        this.players = players;

    }

    public ArrayList<TableMember> getClonedPlayers() {

        players.clear();

        for (int i = 0; i < clonedPlayers.size(); i++) {

            players.add(clonedPlayers.get(i));
        }
        return players;

    }

    public void setClonedPlayers(ArrayList<TableMember> clonedPlayers) {
        this.clonedPlayers = clonedPlayers;
    }

    @Override
    public ArrayList<String> getHeaders() {
        ArrayList<String> header = players.get(0).getAttributeNames();
        return header;
    }

    @Override
    public ArrayList<String> getLine(int line) {
        ArrayList<String> attributes = players.get(line).getAttributes();
        return attributes;
    }

    @Override
    public ArrayList<ArrayList<String>> getLines(int firstLine, int lastLine) {
        ArrayList<ArrayList<String>> lines = new ArrayList<>();
        for (int i = firstLine; i <= lastLine; i++) {
            lines.add(getLine(i));
        }
        return lines;
    }

    //------------------------------------------------------------------------------------------------------------
    @Override
    public int getFirstLineToDisplay() {
        return firstLineToDisplay;
    }

    @Override
    public int getLineToHighlight() {
        return highlightedLine;
    }

    @Override
    public int getLastLineToDisplay() {
        return lastLineToDisplay;
    }

    @Override
    public int getLinesBeingDisplayed() {
        return linesBeingDisplayed;
    }

    @Override
    public void setFirstLineToDisplay(int firstLine) {

        if (firstLine < 0) {
            this.firstLineToDisplay = 0;
        } else if ((firstLine) > (players.size() - getLinesBeingDisplayed())) {
            this.firstLineToDisplay = players.size() - getLinesBeingDisplayed();
        } else {
            this.firstLineToDisplay = firstLine;
        }
    }

    @Override
    public void setLineToHighlight(int highlightedLine) {
        this.highlightedLine = highlightedLine;
    }

    @Override
    public void setLastLineToDisplay(int lastLine) {
        if (lastLine <= (getLinesBeingDisplayed() - 1)) {
            this.lastLineToDisplay = getLinesBeingDisplayed() - 1;
        } else if (lastLine >= players.size()) {
            this.lastLineToDisplay = players.size()-1;
        } else {
            this.lastLineToDisplay = lastLine;
        }
    }

    @Override
    public void setLinesBeingDisplayed(int numberOfLines) {
        this.linesBeingDisplayed = numberOfLines;
    }

    //------------------------------------------------------------------------------------------------------------
    @Override
    public void sort() {
        Sorting cls = new Sorting(getSortField());

        switch (getSortField()) {
            case 0:
                Collections.sort(getTable(), cls.getCompareNumber());
                break;
            case 1:
                Collections.sort(getTable(), cls.getCompareWord());
                break;
            case 2:
                Collections.sort(getTable(), cls.getCompareWord());
                break;
            case 3:
                Collections.sort(getTable(), cls.getCompareHeight());
                break;
            case 4:
                Collections.sort(getTable(), cls.getCompareNumber());
                break;
            case 5:
                Collections.sort(getTable(), cls.getCompareWord());
                break;
            case 6:
                Collections.sort(getTable(), cls.getCompareWord());
                break;
            default:
                break;
        }

    }

    @Override
    public int getSortField() {
        return sortField;
    }

    @Override
    public void SetSortField(int sortField) {
        this.sortField = sortField;
    }
    //------------------------------------------------------------------------------------------------------------

    @Override
    public boolean search(String searchTerm) {
        FootballPlayer mapResult;
        switch(getSearchByField()){
            case 0: mapResult = numberMap.get(searchTerm); break;
            case 1: mapResult = positionMap.get(searchTerm); break;
            case 2: mapResult = nameMap.get(searchTerm); break;
            case 3: mapResult = heightMap.get(searchTerm); break;
            case 4: mapResult = weightMap.get(searchTerm); break;
            case 5: mapResult = hometwonMap.get(searchTerm); break;
            case 6: mapResult = highschoolMap.get(searchTerm); break;  
            default :  mapResult = null; 
        }

        if (mapResult != null) {
            this.setFoundIndex(players.indexOf(mapResult));
            
            this.setFound(true);
            return true;
        } else {
            this.setFoundIndex(-1);
            this.setFound(false);
            return false;
        }
    }

    @Override
    public int getFoundIndex() {
        return this.tableMemberindex;
    }

    @Override
    public void setFoundIndex(int tableMemberindex) {
        this.tableMemberindex = tableMemberindex;
    }

    @Override
    public boolean getFound() {
        return this.searchResult;
    }

    @Override
    public void setFound(boolean searchResult) {
        this.searchResult = searchResult;
    }

    @Override
    public int getSearchByField() {
        return this.fieldIndex;
    }

    @Override
    public void setSearchByField(int fieldIndex) {
        this.fieldIndex = fieldIndex;
    }

    private void generateMap() {
        for (int i = 0; i < players.size(); i++) {
            numberMap.put(players.get(i).getAttribute(0), (FootballPlayer) players.get(i));
            positionMap.put(players.get(i).getAttribute(1), (FootballPlayer) players.get(i));
            nameMap.put(players.get(i).getAttribute(2), (FootballPlayer) players.get(i));
            heightMap.put(players.get(i).getAttribute(3), (FootballPlayer) players.get(i));
            weightMap.put(players.get(i).getAttribute(4), (FootballPlayer) players.get(i));
            hometwonMap.put(players.get(i).getAttribute(5), (FootballPlayer) players.get(i));
            highschoolMap.put(players.get(i).getAttribute(6), (FootballPlayer) players.get(i));
        }

    }

}
