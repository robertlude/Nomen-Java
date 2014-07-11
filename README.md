# Nomen-Java

Number beautifier/namer

## Quick Use

1. Use `git submodule add git@github.com:robertlude/Nomen-Java.git de/ertlu/rob/Nomen` at your Java project source root directory
2. Call `Nomen.format(value)` from your code

## Examples

<table>
    <tr>
        <th>Value</th>
        <th>Mode</th>
        <th>Decimal Places</th>
        <th>Result</th>
    </tr>
    <tr>
        <td>123.456</td>
        <td>--</td>
        <td>--</td>
        <td>123</td>
    </tr>
    <tr>
        <td>123.456</td>
        <td>--</td>
        <td>2</td>
        <td>123.45</td>
    </tr>
    <tr>
        <td>1234.567</td>
        <td>--</td>
        <td>--</td>
        <td>1.234 thousand</td>
    </tr>
    <tr>
        <td>1234.567</td>
        <td>--</td>
        <td>2</td>
        <td>1.234 thousand</td>
    </tr>
    <tr>
        <td>12345.678</td>
        <td>--</td>
        <td>--</td>
        <td>12.34 thousand</td>
    </tr>
    <tr>
        <td>123456.789</td>
        <td>--</td>
        <td>--</td>
        <td>123.4 thousand</td>
    </tr>
    <tr>
        <td>1234567.89</td>
        <td>--</td>
        <td>--</td>
        <td>1.234 million</td>
    </tr>
    <tr>
        <td>1234567890.123</td>
        <td>--</td>
        <td>--</td>
        <td>1.234 billion</td>
    </tr>
    <tr>
        <td>1234567890.123</td>
        <td><code>Nomen.EnglishLongScale</code></td>
        <td>--</td>
        <td>1.234 milliard</td>
    </tr>
    <tr>
        <td>0.0000123456789</td>
        <td><code>Nomen.SIPrefixAbbreviated</code></td>
        <td>--</td>
        <td>12.34&micro;</td>
    </tr>
</table>

## Built-In Formats

* `Nomen.EnglishShortScale`
* `Nomen.EnglishLongScale`
* `Nomen.SIPrefix`
* `Nomen.SIPrefixAbbreviated`
* `Nomen.BytesDecimal`
* `Nomen.BytesDecimalAbbreviated`
* `Nomen.BytesBinary`
* `Nomen.BytesBinaryAbbreviated`

## Usage

### Nomen.format

`String Nomen.format(BigDecimal value[, GroupingMode mode[, int decimalPlaces]]);`

<table>
    <tr>
        <th>Attribute</th>
        <th>Description</th>
        <th>Default</th>
    </tr>
    <tr>
        <td><code>value</code></td>
        <td>The value to format</td>
        <td>--</td>
    </tr>
    <tr>
        <td><code>mode</code></td>
        <td>The details of how to format to string</td>
        <td><code>Nomen.EnglishShortScale</code></td>
    </tr>
    <tr>
        <td><code>decimalPlaces</code></td>
        <td>The number of maximum decimal places to display if <code>1 &lt; abs(value) &lt; groupingMode.groupSize</code></td>
        <td>0</td>
    </tr>
</table>

<table>
  <tr>
    <th>Input</th>
    <th>Output</th>
  </tr>
  <tr>
    <td><code>0 == abs(value)</code></td>
    <td><code>"0"</code> and appropriate label</td>
  </tr>
  <tr>
    <td><code>abs(value) &lt; 1</code></td>
    <td>4 significant digits and appropriate label
  </tr>
  <tr>
    <td><code>1 &lt; abs(value) &lt; groupingMode.groupSize</code></td>
    <td>Full precision truncated at specified decimal place and appropriate label</td>
  </tr>
  <tr>
    <td><code>groupingMode.groupSize &lt; abs(value)</code></td>
    <td>4 significant digits and appropriate label
  </tr>
</table>

### Nomen.GroupingMode

`new Nomen.GroupingMode(int groupSize, String labelMid, String[] labelLarge, String[] labelSmall)`

`new Nomen.GroupingMode(int groupSize, String labelMid, List<String> labelLarge, List<String> labelSmall)`

<table>
    <tr>
        <th>Attribute</th>
        <th>Description</th>
    </tr>
    <tr>
        <td><code>groupSize</code></td>
        <td>Determines the value grouping size. Use 1000 for English and metric scales. Use 1024 for byte sizes.</td>
    </tr>
    <tr>
        <td><code>labelMid</code></td>
        <td>The suffix to use if a value is less than <code>groupSize</code> and greater than or equal to one</td>
    </tr>
    <tr>
        <td><code>labelLarge</code></td>
        <td>A list of suffixes to use if a value is greater than or equal to <code>groupSize</code></td>
    </tr>
    <tr>
        <td><code>labelSmall</code></td>
        <td>A list of suffixes to use if a value is less than 1</td>
    </tr>
</table>

*See source for example implementations*
