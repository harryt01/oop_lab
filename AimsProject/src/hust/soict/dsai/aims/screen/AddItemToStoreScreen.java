package hust.soict.dsai.aims.screen;

public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected Cart cart;
    protected String title = "Add item to store";
    protected String label = "ADD ITEM TO STORE";
    protected JTextField tfId = new JTextField(10);
    protected JTextField tfTitle = new JTextField(10);
    protected JTextField tfCategory = new JTextField(10);
    protected JTextField tfCost = new JTextField(10);
 
    public AddItemToStoreScreen(Store store, Cart cart, String title, String label) {
        this.store = store;
        this.cart = cart;
        this.title = title;
        this.label = label;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createCenter(), BorderLayout.CENTER);
        cp.add(createNorth(), BorderLayout.NORTH);

        setVisible(true);
        setTitle(title);
        setSize(1024, 768);
    }

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenu smUpdateStore = new JMenu("Update Store");
        MenuListener menuListener = new MenuListener(store, cart);
        JMenuItem addBook = new JMenuItem("Add Book");
        addBook.addActionListener(menuListener);
        smUpdateStore.add(addBook);
        JMenuItem addCD = new JMenuItem("Add CD");
        addCD.addActionListener(menuListener);
        smUpdateStore.add(addCD);
        JMenuItem addDVD = new JMenuItem("Add DVD");
        addDVD.addActionListener(menuListener);
        smUpdateStore.add(addDVD);

        menu.add(smUpdateStore);
        JMenuItem viewStore = new JMenuItem("View store");
        viewStore.addActionListener(menuListener);
        menu.add(viewStore);
        JMenuItem viewCart = new JMenuItem("View cart");
        viewCart.addActionListener(menuListener);
        menu.add(viewCart);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel(label);
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        ButtonListener buttonListener = new ButtonListener(store, cart);
        JButton add = new JButton("Add");
        add.setPreferredSize(new Dimension(100, 50));
        add.setMaximumSize(new Dimension(100, 50));
        add.addActionListener(buttonListener);

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(add);
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    abstract JPanel createCenter();

    void addInputRow(JPanel center, String att, JTextField tf) {
        JLabel l = new JLabel(att, JLabel.LEFT);
        l.setLabelFor(tf);
        center.add(tf);
        center.add(l);
    }

    public abstract Media createMedia();

    //ButtonListener for View Cart
    private class ButtonListener implements ActionListener {
        private Store store;
        private Cart cart;

        public ButtonListener(Store store, Cart cart) {
            super();
            this.store = store;
            this.cart = cart;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String button = e.getActionCommand();
            if (button.equals("Add")) {
                Media media = createMedia();
                try {
                    store.addMedia(media);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                new StoreScreen(store, cart);
                setVisible(false);
                dispose();
            }
        }
    }

    //MenuListener for Menu Option
    private class MenuListener implements ActionListener {
        private Store store;
        private Cart cart;

        public MenuListener(Store store, Cart cart) {
            super();
            this.store = store;
            this.cart = cart;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String menu = e.getActionCommand();
            if (menu.equals("Add Book")) {
                new AddBookToStore(store, cart);
                setVisible(false);
                dispose();
            } else if (menu.equals("Add CD")) {
                new AddCDToStore(store, cart);
                setVisible(false);
                dispose();
            } else if (menu.equals("Add DVD")) {
                new AddDVDToStoreScreen(store, cart);
                setVisible(false);
                dispose();
            } else if (menu.equals("View store")) {
                new StoreScreen(store, cart);
                setVisible(false);
                dispose();
            } else if (menu.equals("View cart")) {
                new CartScreen(cart);
                setVisible(false);
                dispose();
            }
        }
    }
}
