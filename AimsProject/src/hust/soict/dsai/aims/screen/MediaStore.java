package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.media.Playable;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.print.attribute.standard.Media;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import java.awt.Color;
import javax.swing.BorderFactory;

public class MediaStore extends JPanel {
    private Media media;
    public MediaStore(Media media) {
        this.media = media;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new  JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        title.setAlignmentX(TOP_ALIGNMENT);
        
        JLabel cost = new JLabel("" + media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        container.add(new JButton("Add to cart"));
        if(media instanceof Playable) {
            container.add(new JButton("Play"));
        }

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue();
        this.add(container));

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void playMedia(Playable media) {
        JDialog playDialog = new JDialog();
        playDialog.setTitle("Playing Media");
        playDialog.setSize(300, 200);
        playDialog.setLocationRelativeTo(null);

        JLabel lblMessage = new JLabel("Now Playing: " + media.getTitle(), JLabel.CENTER);
        lblMessage.setFont(new Font(lblMessage.getFont().getName(), Font.PLAIN, 16));

        playDialog.add(lblMessage);
        playDialog.setVisible(true);
    }
}
