package lkk.report;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.exception.DRException;

public class Druk {
	public static void repo(){	
		
		StyleBuilder boldStyle         = stl.style().bold(); 
		StyleBuilder boldCenteredStyle = stl.style(boldStyle)
		                                    .setHorizontalAlignment(HorizontalAlignment.CENTER);
		StyleBuilder boldCenteredStyleFont = stl.style(boldCenteredStyle).setFontSize(18);
		StyleBuilder boldCenteredStyleFontItalic = stl.style(boldCenteredStyleFont).italic();
		
		try {
			report()			
			.title(cmp.text("Заключення ЛКК").setStyle(boldCenteredStyleFontItalic))
			
			.show(false);
		} catch (DRException e) {
			e.printStackTrace();
		}
	}		 
}
