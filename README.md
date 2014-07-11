# Nomen-Java

Number beautifier/namer

## Examples

<table>
    <tr>
        <th>Value</th>
        <th>Mode</th>
        <th>Decimal Places</th>
        <th>Result</th>
    </tr>
    <tr>
        <td>1998348.9931</td>
        <td>--</td>
        <td>--</td>
        <td>1.998 million</td>
    </tr>
    <tr>
        <td>19983489.931</td>
        <td>--</td>
        <td>--</td>
        <td>19.98 million</td>
    </tr>
    <tr>
        <td>199834899.31</td>
        <td>--</td>
        <td>--</td>
        <td>199.8 million</td>
    </tr>
    <tr>
        <td>1998348993.1</td>
        <td>--</td>
        <td>--</td>
        <td>1.998 billion</td>
    </tr>
    <tr>
        <td>12.34567</td>
        <td>--</td>
        <td>2</td>
        <td>12.34</td>
    </tr>
    <tr>
        <td>0.00004321234</td>
        <td>SIPrefixAbbreviated</td>
        <td>--</td>
        <td>43.21&micro;</td>
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
        <td>The number of maximum decimal places to display if `value` is less than `groupingMode.groupSize` and greater than or equal to 1</td>
        <td>0</td>
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
