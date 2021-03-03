package Controller;

import Model.Model;
import View.View;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JButton;

public class Controller {

    Model model;
    View view;
    public int oldButton;

    public Controller(Model m, View v) {
        model = m;
        view = v;

        oldButton = -1;

        view.CenterInitialSetup(model.getFpData().getLinesBeingDisplayed(), model.getFpData().getHeaders().size());
        view.CenterUpdate(model.getFpData().getLines(model.getFpData().getFirstLineToDisplay(),
                model.getFpData().getLastLineToDisplay()), model.getFpData().getHeaders());
        this.addListeners();

    }

    private void addListeners() {

        for (int i = 0; i < model.getFpData().getHeaders().size(); i++) {
            view.getMf().getMjp().getCp().getJbs().get(i).addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    JButton b = (JButton) ae.getSource();
                    int buttonIndex = model.getFpData().getHeaders().indexOf(b.getText());
                    if (buttonIndex == oldButton) {
                        model.getFpData().setSearchByField(-1);

                        model.getFpData().setTable(model.getFpData().getClonedPlayers());

                        view.CenterUpdate(model.getFpData().getLines(model.getFpData().getFirstLineToDisplay(),
                                model.getFpData().getLastLineToDisplay()), model.getFpData().getHeaders());

                        view.getMf().getMjp().getCp().changeColor(0, (model.getFpData().getHeaders().size() - 1), "white", "black");
                        oldButton = -1;
                        model.getFpData().SetSortField(-1);

                    } else {
                        model.getFpData().SetSortField(model.getFpData().getHeaders().indexOf(b.getText()));
                        model.getFpData().sort();

                        view.CenterUpdate(model.getFpData().getLines(model.getFpData().getFirstLineToDisplay(),
                                model.getFpData().getLastLineToDisplay()), model.getFpData().getHeaders());

                        view.getMf().getMjp().getCp().changeColor(0, (model.getFpData().getHeaders().size() - 1), "white", "black");

                        b.setBackground(Color.DARK_GRAY);
                        b.setForeground(Color.WHITE);

                        model.getFpData().setSearchByField(buttonIndex);
                        view.getMf().getMjp().getNp().getSt().setText("");
                        view.getMf().getMjp().getNp().getSt().setBackground(Color.white);

                        oldButton = model.getFpData().getSortField();

                    }

                }
            });

        }

        view.getMf().getMjp().getNp().getSt().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String searchTerm = view.getMf().getMjp().getNp().getSt().getText();
                boolean ifFound = model.getFpData().search(searchTerm);
                if (ifFound == true) {
                    view.getMf().getMjp().getNp().getSt().setBackground(Color.white);
                    model.getFpData().setFirstLineToDisplay(model.getFpData().getFoundIndex());

                    model.getFpData().setLastLineToDisplay(model.getFpData().getFoundIndex() + model.getFpData().getLinesBeingDisplayed() - 1);

                    view.CenterUpdate(model.getFpData().getLines(model.getFpData().getFirstLineToDisplay(),
                            model.getFpData().getLastLineToDisplay()), model.getFpData().getHeaders());
                    view.getMf().getMjp().getCp().getJbs().get(model.getFpData().getSearchByField()).setBackground(Color.DARK_GRAY);
                    view.getMf().getMjp().getCp().getJbs().get(model.getFpData().getSearchByField()).setForeground(Color.WHITE);

                    if (model.getFpData().getLastLineToDisplay() != model.getFpData().getTable().size() - 1) {
                        view.getMf().getMjp().getCp().changeColor(model.getFpData().getHeaders().size(), ((model.getFpData().getHeaders().size() * 2) - 1), "pink", "black");
                    } else {
                        int item = view.getMf().getMjp().getCp().rowStartingPos(searchTerm, model.getFpData().getHeaders().size());
                        
                        view.getMf().getMjp().getCp().changeColor(item, item + (model.getFpData().getHeaders().size() - 1), "pink", "black");
                    }
                } else {
                    //view.getMf().getMjp().getNp().getSt().setOpaque(true);
                    view.getMf().getMjp().getNp().getSt().setBackground(Color.red);
                    view.CenterUpdate(model.getFpData().getLines(model.getFpData().getFirstLineToDisplay(),
                            model.getFpData().getLastLineToDisplay()), model.getFpData().getHeaders());
                    view.getMf().getMjp().getCp().getJbs().get(model.getFpData().getSearchByField()).setBackground(Color.DARK_GRAY);
                    view.getMf().getMjp().getCp().getJbs().get(model.getFpData().getSearchByField()).setForeground(Color.WHITE);
                }
            }
        }
        );

        view.getMf().getMjp().getCp().addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int units = e.getUnitsToScroll();

                model.getFpData().setFirstLineToDisplay(model.getFpData().getFirstLineToDisplay() + units);

                model.getFpData().setLastLineToDisplay(model.getFpData().getLastLineToDisplay() + units);

                view.CenterUpdate(model.getFpData().getLines(model.getFpData().getFirstLineToDisplay(),
                        model.getFpData().getLastLineToDisplay()), model.getFpData().getHeaders());

                if (model.getFpData().getSortField() == -1) {
                    int pass = -1;
                } else {
                    view.getMf().getMjp().getCp().getJbs().get(model.getFpData().getSortField()).setBackground(Color.DARK_GRAY);
                    view.getMf().getMjp().getCp().getJbs().get(model.getFpData().getSortField()).setForeground(Color.WHITE);
                }

                int item = view.getMf().getMjp().getCp().rowStartingPos((view.getMf().getMjp().getNp().getSt().getText()), model.getFpData().getHeaders().size());
                if (item == -1) {
                    int pass = -1;
                } else {
                    view.getMf().getMjp().getCp().changeColor(item, item + (model.getFpData().getHeaders().size() - 1), "pink", "black");
                }

            }
        }
        );

    }

}
