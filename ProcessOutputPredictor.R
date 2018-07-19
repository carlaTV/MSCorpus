Home <- setwd("~/Google Drive File Stream/La meva unitat/PhD/MSAnnotations/Resultats")
JavaFolder <- setwd("~/Documents/Carla/wekaTest")
output_predictor <- read_csv("output_predictor.csv", col_names = FALSE)
#ens quedem amb la primera columna:
output_predictor <- output_predictor[,1]

## add instance number:
index <- c(1:length(output_predictor$X1))
#funcions per detectar els parells i els imparells

odd <- function(x) x%%2 != 0 
evenb <- function(x) !odd(x) 

## odd positions contain instances and even, the predicted output

instances <- output_predictor[odd(index),]
classification <- output_predictor[evenb(index),]

library(qdapRegex)
# add mark to end of instance
instances$X1 <- paste(instances$X1, '|', sep = '')

#capture instance value
inst_value <- rep('', length(instances$X1))

for (i in 1:length(instances$X1)){
  inst_value[i] <- rm_between(instances$X1[i], 'instance:', '|', extract=TRUE)[[1]]
}


instances <- cbind(instances, inst_value)

#capture classification value
class_value <- rep('',length(classification$X1))
for (i in 1:length(classification$X1)){
  class_value[i] <- rm_between(classification$X1[i], 'Classification:', '|', extract=TRUE)[[1]]
}

classification <- cbind(classification, class_value)

### compare results
comparison <- (classification[2] == instances[2])

ok <- which(comparison == 'TRUE')
ko <- which(comparison == 'FALSE')


# percentage of well classified instances:
perc <- (length(ok) / length(comparison)) *100

#gather sentence + classification + original instances
output <- data.frame(classification$class_value, instances$inst_value)

Home
write.csv(output, file = 'OutputSentence6 _multiplied.csv')
