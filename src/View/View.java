package View;

import java.util.ArrayList;

public class View
{
//

    private InitialFrame mf;

    public View()
    {
        mf = new InitialFrame();
    }

    public InitialFrame getMf() {
        return mf;
    }

    public void setMf(InitialFrame mf) {
        this.mf = mf;
    }
    
    public void CenterInitialSetup(int n, int m)
    {  
        mf.getMjp().getCp().createButtons(n,m);
    }

    public void CenterUpdate(ArrayList<ArrayList<String>> arrOFarr, ArrayList<String> headers)
    {
        mf.getMjp().getCp().displayDataOnButtons(arrOFarr,headers);
    }
}
