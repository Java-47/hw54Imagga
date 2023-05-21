package imagga.dto;

import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ImaggaDto {
	Result result;

	@Getter
	public static class Result {
		Colors colors;
		Status status;
	}

	@Getter
	public static class Colors {
		private List<ColorDetails> background_colors;
		private Double color_percent_threshold;
		private Integer color_variance;
		private List<ColorDetails> foreground_colors;
		private List<ColorDetails> image_colors;
		private Double object_percentage;

		public void printColors() {
			System.out.println("color name | parent color name | coverage percent \n");
			printColors(getBackground_colors());
			printColors(getForeground_colors());
			printColors(getImage_colors());
		}

		private void printColors(List<ColorDetails> colorDetailsList) {
			colorDetailsList.stream()
					.forEach(colorDetails -> System.out.printf("%s | %s | %.3f%% \n",
							colorDetails.getClosest_palette_color(), colorDetails.getClosest_palette_color_parent(),
							colorDetails.getPercent()));
		}
	}

	@Getter
	public static class ColorDetails {
		private Integer b;
		private String closest_palette_color;
		private String closest_palette_color_html_code;
		private String closest_palette_color_parent;
		private Double closest_palette_distance;
		private Integer g;
		private String html_code;
		private Double percent;
		private Integer r;
	}

	@Getter
	public static class Status {
		private String text;
		private String type;
	}

}
