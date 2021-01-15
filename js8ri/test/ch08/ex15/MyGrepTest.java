package ch08.ex15;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MyGrepTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String ls = System.lineSeparator();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void 全ての引数がNonNullならば何も表示されない() {
        MyGrep.main(new String[]{"[Ww]onder[A-z]+", "sample/AliceInWonderland.txt"});
        // 含まれる: Wonderland, wonderful, wondered, etc.
        // 含まれない: wonder

        System.out.flush();
        final String expected = "There was nothing so very remarkable in that; nor did Alice think it so very much out of the way to hear the Rabbit say to itself, “Oh dear! Oh dear! I shall be late!” (when she thought it over afterwards, it occurred to her that she ought to have wondered at this, but at the time it all seemed quite natural); but when the Rabbit actually took a watch out of its waistcoat pocket, and looked at it, and then hurried on, Alice started to her feet, for it flashed across her mind that she had never before seen a rabbit with either a waistcoat pocket, or a watch to take out of it, and burning with curiosity, she ran across the field after it, and fortunately was just in time to see it pop down a large rabbit hole under the hedge." + ls
                + "There were doors all round the hall, but they were all locked; and when Alice had been all the way down one side and up the other, trying every door, she walked sadly down the middle, wondering how she was ever to get out again." + ls
                + "For a minute or two she stood looking at the house, and wondering what to do next, when suddenly a footman in livery came running out of the wood  (she considered him to be a footman because he was in livery: otherwise, judging by his face only, she would have called him a fish)  and rapped loudly at the door with his knuckles. It was opened by another footman in livery, with a round face, and large eyes like a frog; and both footmen, Alice noticed, had powdered hair that curled all over their heads. She felt very curious to know what it was all about, and crept a little way out of the wood to listen." + ls
                + "“Come on, then!” roared the Queen, and Alice joined the procession, wondering very much what would happen next." + ls
                + "She was looking about for some way of escape, and wondering whether she could get away without being seen, when she noticed a curious appearance in the air: it puzzled her very much at first, but, after watching it a minute or two, she made it out to be a grin, and she said to herself “It’s the Cheshire Cat: now I shall have somebody to talk to.”" + ls
                + "“I dare say you’re wondering why I don’t put my arm round your waist,” the Duchess said after a pause: “the reason is, that I’m doubtful about the temper of your flamingo. Shall I try the experiment?”" + ls
                + "Alice was thoroughly puzzled. “Does the boots and shoes!” she repeated in a wondering tone." + ls
                + "Alice said nothing; she had sat down with her face in her hands, wondering if anything would ever happen in a natural way again." + ls
                + "“Oh, I’ve had such a curious dream!” said Alice, and she told her sister, as well as she could remember them, all these strange Adventures of hers that you have just been reading about; and when she had finished, her sister kissed her, and said, “It was a curious dream, dear, certainly: but now run in to your tea; it’s getting late.” So Alice got up and ran off, thinking while she ran, as well she might, what a wonderful dream it had been." + ls
                + "But her sister sat still just as she left her, leaning her head on her hand, watching the setting sun, and thinking of little Alice and all her wonderful Adventures, till she too began dreaming after a fashion, and this was her dream:  " + ls
                + "So she sat on, with closed eyes, and half believed herself in Wonderland, though she knew she had but to open them again, and all would change to dull reality  the grass would be only rustling in the wind, and the pool rippling to the waving of the reeds  the rattling teacups would change to tinkling sheep bells, and the Queen’s shrill cries to the voice of the shepherd boy  and the sneeze of the baby, the shriek of the Gryphon, and all the other queer noises, would change (she knew) to the confused clamour of the busy farm yard  while the lowing of the cattle in the distance would take the place of the Mock Turtle’s heavy sobs." + ls
                + "Lastly, she pictured to herself how this same little sister of hers would, in the after time, be herself a grown woman; and how she would keep, through all her riper years, the simple and loving heart of her childhood: and how she would gather about her other little children, and make their eyes bright and eager with many a strange tale, perhaps even with the dream of Wonderland of long ago: and how she would feel with all their simple sorrows, and find a pleasure in all their simple joys, remembering her own child life, and the happy summer days." + ls;
        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void 引数が0個のときに例外() {
        try {
            MyGrep.main(new String[]{});
        } catch (final IllegalArgumentException exception) {
            assertThat(exception.getMessage(), is("How to use: MyGrep <regular expression> <path>"));
        }
    }

    @Test
    public void 引数が1個のときに例外() {
        try {
            MyGrep.main(new String[]{"keyword"});
        } catch (final IllegalArgumentException exception) {
            assertThat(exception.getMessage(), is("How to use: MyGrep <regular expression> <path>"));
        }
    }
}